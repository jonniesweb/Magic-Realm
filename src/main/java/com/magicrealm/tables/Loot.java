package com.magicrealm.tables;

import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Discoverable;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.treasure.TreasureChit;
import com.magicrealm.server.ServerGameState;

public class Loot extends Table {
	
	public Loot(ServerGameState gameState, String clientId) {
		super(gameState, clientId);
	}
	
	@Override
	public void one() {
		// take top treasure in pile
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 1);
	}

	@Override
	public void two() {
		// take 2nd treasure in pile
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 2);
	}
	
	@Override
	public void three() {
		// take 3rd treasure in pile	
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 3);
	}
	
	@Override
	public void four() {
		// take 4th treasure in pile
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 4);
	}
	
	@Override
	public void five() {
		// take 5th treasure in pile
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 5);
	}
	
	@Override
	public void six() {
		// take 6th treasure in pile
		MRCharacter character = getGameState().getCharacter(getClientId());
		takeTreasure(character, 6);
	}

	private void takeTreasure(MRCharacter character, int i) {
		SiteChit site = null;
		
		for (Discoverable disc : character.getDiscoveries()) {
			if (getChitAtLocation() != null && getChitAtLocation().equals(disc) && disc instanceof SiteChit) {
				site = (SiteChit) getChitAtLocation();
				break;
			}
		}
		
		if (site == null) {
			getGameState().getClientService(getClientId()).sendMessage("Noting found when Looting");
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
		
		getGameState().getClientService(getClientId()).sendMessage("Looted " + treasure.getGold() + " gold");
	}
	
}
