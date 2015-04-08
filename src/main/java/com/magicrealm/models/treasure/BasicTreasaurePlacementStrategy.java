package com.magicrealm.models.treasure;

import java.util.List;
import java.util.Random;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.treasure.TreasureChit.treasure;

/**
 * Just place gold pieces for large and small treasures.
 * 
 * Small treasures: 10, 20, 30 gold
 * Large treasures: 40, 50 gold
 */
public class BasicTreasaurePlacementStrategy extends
		TreasurePlacementStrategy {
	
	public BasicTreasaurePlacementStrategy() {
	}

	@Override
	public void placeTreasure(MagicRealmHexEngineModel model) {
		List<SiteChit> chits = model.getAllSiteChits();
		
		for (SiteChit chit : chits) {
			setupTreasure(chit);
		}
	}
	
	private static void setupTreasure(SiteChit chit) {
		switch (chit.getSiteType()) {
		case altair:
			addGold(chit, 0, 4);
			break;
		case cairns:
			addGold(chit, 6, 1);
			break;
		case hoard:
			addGold(chit, 4, 5);
			break;
		case lair:
			addGold(chit, 4, 3);
			break;
		case pool:
			addGold(chit, 6, 3);
			break;
		case shrine:
			addGold(chit, 2, 2);
			break;
		case statue:
			addGold(chit, 2, 1);
			break;
		case vault:
			addGold(chit, 0, 5);
			break;
		case lost_castle:
			addGoldToLostSite(chit);
			break;
		case lost_city:
			addGoldToLostSite(chit);
			break;
		default:
			break;
		}
	}
	
	private static void addGoldToLostSite(SiteChit lostSite) {
		for(MapChit chit: lostSite.getExtraChits()) {
			if(chit instanceof SiteChit)
				setupTreasure((SiteChit) chit);
		}
	}

	private static void addGold(SiteChit chit, int small, int large) {
		Random r = new Random();
		
		// small treasures
		for (int i = 0; i < small; i++) {
			TreasureChit treasureChit = new TreasureChit(treasure.small);
			// gold: 10, 20, 30
			treasureChit.setGold((r.nextInt(3) + 1) * 10);
			// fame: 5, 10, 15
			treasureChit.setFame((r.nextInt(3) + 1) * 5);
			// notoriety: 2, 4, 6, 8, 10
			treasureChit.setNotoriety((r.nextInt(5) + 1) * 2);
			chit.addTreasure(treasureChit);
		}
		
		// large treasures
		for (int i = 0; i < large; i++) {
			TreasureChit treasureChit = new TreasureChit(treasure.large);
			// gold: 40, 50
			treasureChit.setGold((r.nextInt(2) + 4) * 10);
			// fame: 20, 25, 30
			treasureChit.setFame((r.nextInt(3) + 4) * 5);
			// notoriety: 12, 14, 16, 18, 20
			treasureChit.setNotoriety((r.nextInt(5) + 6) * 2);
			chit.addTreasure(treasureChit);
		}
	}
	
}
