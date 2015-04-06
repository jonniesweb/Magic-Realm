package com.magicrealm.server.state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.SiteChit.site;
import com.magicrealm.models.monsters.GoblinAxe;
import com.magicrealm.models.monsters.GoblinGreatSword;
import com.magicrealm.models.monsters.GoblinSpear;
import com.magicrealm.models.monsters.HeavySpider;
import com.magicrealm.models.monsters.MRMonster;
import com.magicrealm.models.monsters.Ogre;
import com.magicrealm.models.monsters.Wolf;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.ProbabilityCalculator;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public class BirdsongState extends ServerState {
	
	private Map<String, BirdsongActivities> activities = new HashMap<>();
	private final Log log = LogFactory.getLog(BirdsongState.class);

	public BirdsongState(ServerGameState instance) {
		super(instance);
	}

	public void init() {
		// notify clients of birdsong
		for (IClientService service : getGameState().getClientServices()) {
			service.birdsongStarted(getGameState().getBoard());
		}		
		summonMonsters();
	}
	
	/**
	 * Clients queue activities in this state, then in the next state they get
	 * executed.
	 * @param clientId
	 * @param activities
	 */
	public void addActivities(String clientId, BirdsongActivities activities) {
		// TODO: verify that the activities are valid before adding it 
		this.activities.put(clientId, activities);
		
		/*
		 * Check if everyone has submitted their activities. If so, change the
		 * state to a new one.
		 */
		if (this.activities.size() == getGameState().getNumberOfPlayers()) {
			DaylightState state = new DaylightState(getGameState(), this.activities);
			getGameState().setState(state);
			state.init();
		}
	}
	
	public void summonMonsters() {
		Iterator<MRCharacter> it = getGameState().getCharacters().iterator();
		Result roll = ProbabilityCalculator.getResult(it.next());
		switch (roll) {
		case ONE:
			summonSiteMonsters(site.hoard, new GoblinGreatSword());
			summonSiteMonsters(site.lair, new GoblinGreatSword());
			break;
		case TWO:
			summonSiteMonsters(site.shrine, new GoblinAxe());
			summonSiteMonsters(site.altair, new GoblinSpear());
			break;
		case THREE:
//			summonSiteMonsters(site.pool, new Octupus());
			break;
		case FOUR:
			summonSiteMonsters(site.vault, new Ogre());
			break;
		case FIVE:
			summonSiteMonsters(site.cairns, new HeavySpider());
			summonSiteMonsters(site.statue, new Wolf());
			break;
		default:
			break;
		}
	}
	
	public void summonSiteMonsters(site site1, MRMonster monster) {
		for(TileType tt: TileType.values()) {
			GameTile tile = getGameState().getBoard().getTile(tt);
			ClearingMapChit chit = tile.getSiteSoundChit();
			SiteChit siteChit;
			if(chit instanceof SiteChit) {
				siteChit = (SiteChit) chit;
			} else {
				continue;
			}
			
			if(siteChit != null && (siteChit.getSiteType() == site.lost_castle || siteChit.getSiteType() == site.lost_city)) {
				for(MapChit c: siteChit.getExtraChits()) {
					SiteChit siteChit2 = null;
					if(c instanceof SiteChit) {
						 siteChit2 = (SiteChit) c;
					}
					if(siteChit2 != null && siteChit2.getSiteType() == site1) {
						getGameState().getBoard().placeChit(tt, siteChit2.getClearing(), monster);
						
						// notify clients of monsters
						for (IClientService service : getGameState().getClientServices()) {
							service.sendMessage("Monsters were summoned at " + site1.name());
						}
					}
				}
			} else if(siteChit != null && siteChit.getSiteType() == site1) {
				getGameState().getBoard().placeChit(tt, siteChit.getClearing(), monster);
				
				// notify clients of monsters
				for (IClientService service : getGameState().getClientServices()) {
					service.sendMessage("Monsters were summoned at " + site1.name());
				}
			}
		}
		
	}
	
}
