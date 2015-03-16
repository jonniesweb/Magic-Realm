package com.magicrealm.models;

import java.util.ArrayList;
import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class Combat {
	
	List<Combatant> combatants = new ArrayList<Combat.Combatant>();
	ServerGameState gameState;
	
	public Combat(ServerGameState gameState, List<MRCharacter> characters) {
		for(MRCharacter c: characters) {
			combatants.add(new Combatant(c, getAttackers(characters, c)));			
		}
		this.gameState = gameState;
	}
	
	// events
	// targeting
	// luring
	// random assignment of remaining monsters
	public void encounter() {
		
		// choose target
		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.character.getCharacterType());
			c.setTarget((MRCharacter) client.clientSelect(c.attackers.toArray(new MRCharacter[0]), "Choose a target"));
		}
	}
	
	public void melee() {
		
	}
	
	public List<MRCharacter> getAttackers(List<MRCharacter> characters, MRCharacter attacker) {
		List<MRCharacter> newList = new ArrayList<MRCharacter>();
		for(MRCharacter c: characters) {
			if((attacker.isFoundHidden() || !c.isHidden()) && c.getCharacterType() != attacker.getCharacterType())
				newList.add(c);
		}
		return newList;
	}
	
	protected class Combatant {
		MRCharacter character;
		List<MRCharacter> attackers;
		MRCharacter target;
		
		public Combatant(MRCharacter character, List<MRCharacter> attackers) {
			
			this.character = character;
			this.attackers = attackers;
		}
		
		public void setTarget(MRCharacter target) {
			this.target = target;
		}
	}

}
