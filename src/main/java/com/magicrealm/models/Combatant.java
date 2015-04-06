package com.magicrealm.models;

import java.io.Serializable;

import com.magicrealm.models.armors.Armor.Protection;

public abstract class Combatant implements Serializable {
	
	private Combatant target = null;
	private ActionChit mainActionChit = null;
	private Protection attackDirection = null;
	private ActionChit defenseChit = null;
	private Protection defenseDirection = null;
	
	public void setTarget(Combatant target) {
		this.target = target;
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

	public Combatant getTarget() {
		return target;
	}

}
