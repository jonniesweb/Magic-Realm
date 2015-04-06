package com.magicrealm.models;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;

public class CharacterCombatant extends Combatant {
	
	private MRCharacter.CharacterType character;
	
	public CharacterCombatant(MRCharacter character) {
		this.character = character.getCharacterType();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		CharacterCombatant charObj;
		if(obj instanceof CharacterCombatant) {
			charObj = (CharacterCombatant) obj;
		} else
			return false;
		return character.equals(charObj.getCharacter());
	}
	
	public CharacterType getCharacter() {
		return character;
	}

	public void setCharacter(CharacterType character) {
		this.character = character;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return character.name();
	}

}
