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
import com.magicrealm.models.chits.SiteChit.Site;
import com.magicrealm.models.monsters.Giant;
import com.magicrealm.models.monsters.GoblinAxe;
import com.magicrealm.models.monsters.GoblinGreatSword;
import com.magicrealm.models.monsters.GoblinSpear;
import com.magicrealm.models.monsters.HeavyBat;
import com.magicrealm.models.monsters.HeavySpider;
import com.magicrealm.models.monsters.MRMonster;
import com.magicrealm.models.monsters.Octopus;
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
		incrementDay();
		// notify clients of birdsong
		for (IClientService service : getGameState().getClientServices()) {
			service.birdsongStarted(getGameState().getBoard(), getDay());
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
			summonSiteMonsters(Site.hoard, new Giant());
			summonSiteMonsters(Site.lair, new Giant());
			summonSiteMonsters(Site.shrine, new Giant());
			break;
		case TWO:
			summonSiteMonsters(Site.shrine, new GoblinGreatSword(), 3);
			summonSiteMonsters(Site.altair, new GoblinSpear(), 3);
			summonSiteMonsters(Site.vault, new GoblinAxe(), 3);
			break;
		case THREE:
			summonSiteMonsters(Site.pool, new Octopus(), 2);
			summonSiteMonsters(Site.lair, new Ogre(), 2);
			summonSiteMonsters(Site.hoard, new Ogre(), 2);
			break;
		case FOUR:
			summonSiteMonsters(Site.vault, new Ogre());
			summonSiteMonsters(Site.cairns, new Wolf(), 3);
			summonSiteMonsters(Site.statue, new Wolf(), 2);
			break;
		case FIVE:
			summonSiteMonsters(Site.cairns, new HeavySpider(), 3);
			summonSiteMonsters(Site.statue, new Wolf(), 2);
			break;
		case SIX:
			summonSiteMonsters(Site.vault, new HeavyBat(), 2);
			summonSiteMonsters(Site.altair, new HeavyBat());
			summonSiteMonsters(Site.shrine, new HeavyBat());
			break;
		default:
			break;
		}
	}
	
	private void summonSiteMonsters(Site site, MRMonster monster) {
		summonSiteMonsters(site, monster, 1);
	}
	
	private void summonSiteMonsters(Site site1, MRMonster monster, int count) {
		for(TileType tt: TileType.values()) {
			GameTile tile = getGameState().getBoard().getTile(tt);
			ClearingMapChit chit = tile.getSiteSoundChit();
			SiteChit siteChit;
			if(chit instanceof SiteChit) {
				siteChit = (SiteChit) chit;
			} else {
				continue;
			}
			
			if(siteChit != null && (siteChit.getSiteType() == Site.lost_castle || siteChit.getSiteType() == Site.lost_city)) {
				for(MapChit c: siteChit.getExtraChits()) {
					SiteChit siteChit2 = null;
					if(c instanceof SiteChit) {
						 siteChit2 = (SiteChit) c;
					}
					if(siteChit2 != null && siteChit2.getSiteType() == site1) {
						try {
							for(int i = 0; i < count; i++) {								
								getGameState().getBoard().placeChit(tt, siteChit2.getClearing(), monster);
							}
						} catch (Exception e) {
							log.error("no clearing exists to place this monster in", e);
						}
						
						// notify clients of monsters
						for (IClientService service : getGameState().getClientServices()) {
							service.sendMessage("Monsters were summoned at " + site1.name());
						}
					}
				}
			} else if(siteChit != null && siteChit.getSiteType() == site1) {
				try {
					for(int i = 0; i < count; i++) {
						getGameState().getBoard().placeChit(tt, siteChit.getClearing(), monster);
					}
				} catch (NullPointerException e) {
					// hack to catch NPE when no clearing exists in that tile
					log.error("no clearing exists to place this monster in", e);
				}
				
				// notify clients of monsters
				for (IClientService service : getGameState().getClientServices()) {
					service.sendMessage("Monsters were summoned at " + site1.name());
				}
			}
		}
		
	}
	
}
