package com.magicrealm.models;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.GameState;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.treasure.TreasureChit;
import com.magicrealm.utils.GameLog;

public class Loot extends Table {
	
	private Log log = LogFactory.getLog(Loot.class);
	
	@Override
	public void one() {
		// take top treasure in pile
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 1);
	}

	@Override
	public void two() {
		// take 2nd treasure in pile
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 2);
	}
	
	@Override
	public void three() {
		// take 3rd treasure in pile	
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 3);
	}
	
	@Override
	public void four() {
		// take 4th treasure in pile
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 4);
	}
	
	@Override
	public void five() {
		// take 5th treasure in pile
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 5);
	}
	
	@Override
	public void six() {
		// take 6th treasure in pile
		MRCharacter character = GameState.getInstance().getCharacter();
		takeTreasure(character, 6);
	}

	private void takeTreasure(MRCharacter character, int i) {
		SiteChit site = null;
		
		for (Discoverable disc : character.getDiscoveries()) {
			if (getChitAtLocation() != null && getChitAtLocation().equals(disc) && disc instanceof SiteChit) {
				site = (SiteChit) getChitAtLocation();
				log.info("found site chit at characters location " + site);
				break;
			}
		}
		
		if (site == null) {
			GameLog.log("Noting found when Looting", "did not find a site chit when looting");
			return;
		}
		
		List<TreasureChit> treasures = site.getTreasures();
		
		// if larger than the list of treasures, get the bottom
		TreasureChit treasure = null;
		if (i > treasures.size()) {
			treasure = treasures.remove(treasures.size() - 1);
			character.setGold(character.getGold() + treasure.getGold());
			
		} else {
			treasure = treasures.remove(i);
			character.setGold(character.getGold() + treasure.getGold());
		}
		
		GameLog.log("Looted " + treasure.getGold() + " gold", "found treasure: " + treasure.getGold() + " " + treasure);
	}
	
}
