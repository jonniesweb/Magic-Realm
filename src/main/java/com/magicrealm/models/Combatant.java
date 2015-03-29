package com.magicrealm.models;

import java.io.Serializable;
import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.armors.Armor.Protection;
import com.magicrealm.models.weapons.Weapon;

public class Combatant implements Serializable {
	private MRCharacter character;
	private List<Combatant> attackers;
	private Combatant target;
	private ActionChit fightChit;
	private Protection attackDirection;
	private ActionChit moveChit;
	private Protection defenseDirection;
	
	public Combatant(MRCharacter character) {
		this.character = character;
	}
	
	public void setTarget(Combatant target) {
		this.target = target;
	}
	
	public void setAttackers(List<Combatant> attackers) {
		this.attackers = attackers;
	}
	
	public Weight getTotalHarm(boolean hitsArmor) {
		Weapon wep = character.getActiveWeapon();
		Weight harm;
		
		if(hitsArmor)
			harm = wep.getInflictedHarmThroughArmor();
		else
			harm = wep.getInflictedHarm();
		
		if(fightChit.getStrength().compareTo(wep.getHarm()) > 0)
			harm.increment(1);
		return harm;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return character.equals(obj);
	}

	public MRCharacter getCharacter() {
		return character;
	}

	public void setCharacter(MRCharacter character) {
		this.character = character;
	}

	public ActionChit getFightChit() {
		return fightChit;
	}

	public void setFightChit(ActionChit fightChit) {
		this.fightChit = fightChit;
	}

	public Protection getAttackDirection() {
		return attackDirection;
	}

	public void setAttackDirection(Protection attackDirection) {
		this.attackDirection = attackDirection;
	}

	public ActionChit getMoveChit() {
		return moveChit;
	}

	public void setMoveChit(ActionChit moveChit) {
		this.moveChit = moveChit;
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
