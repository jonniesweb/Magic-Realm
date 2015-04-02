package com.magicrealm.models;

import java.io.Serializable;
import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.models.armors.Armor.Protection;

public class Combatant implements Serializable {
	private MRCharacter.CharacterType character;
	private List<Combatant> attackers;
	private Combatant target;
	private ActionChit mainActionChit;
	private Protection attackDirection;
	private ActionChit defenseChit;
	private Protection defenseDirection;
	
	public Combatant(MRCharacter character) {
		this.character = character.getCharacterType();
	}
	
	public void setTarget(Combatant target) {
		this.target = target;
	}
	
	public void setAttackers(List<Combatant> attackers) {
		this.attackers = attackers;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Combatant charObj;
		if(obj instanceof Combatant) {
			charObj = (Combatant) obj;
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

	public ActionChit getMainActionChit() {
		return mainActionChit;
	}

	public void setMainActionChit(ActionChit fightChit) {
		this.mainActionChit = fightChit;
	}

	public Protection getAttackDirection() {
		return attackDirection;
	}

	public void setAttackDirection(Protection attackDirection) {
		this.attackDirection = attackDirection;
	}

	public ActionChit getDefenseChit() {
		return defenseChit;
	}

	public void setDefenseChit(ActionChit moveChit) {
		this.defenseChit = moveChit;
	}

	public Protection getDefenseDirection() {
		return defenseDirection;
	}

	public void setDefenseDirection(Protection defenseDirection) {
		this.defenseDirection = defenseDirection;
	}

	public List<Combatant> getAttackers() {
		return attackers;
	}

	public Combatant getTarget() {
		return target;
	}

}
