package com.magicrealm.models.treasure;

import java.util.List;
import java.util.Random;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
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
	
	public BasicTreasaurePlacementStrategy(MagicRealmHexEngineModel model) {
		super(model);
	}

	@Override
	public void placeTreasure() {
		List<SiteChit> chits = model.getAllSiteChits();
		
		for (SiteChit chit : chits) {
			
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
			default:
				break;
			}
		}
	}

	private void addGold(SiteChit chit, int small, int large) {
		Random r = new Random();
		
		for (int i = 0; i < small; i++) {
			TreasureChit treasureChit = new TreasureChit(treasure.small);
			treasureChit.setGold((r.nextInt(3) + 1) * 10);
			chit.addTreasure(treasureChit);
		}
		
		for (int i = 0; i < large; i++) {
			TreasureChit treasureChit = new TreasureChit(treasure.large);
			treasureChit.setGold((r.nextInt(2) + 4) * 10);
			chit.addTreasure(treasureChit);
		}
	}
	
}
