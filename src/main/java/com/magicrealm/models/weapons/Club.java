package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Club extends Weapon {
	
	public Club() {
		super("Club", Attack.STRIKING, 8, 2, Weight.HEAVY);
	}
	
	@Override
	public void setAlertStats() {
		speed = 4;
		weight = Weight.TREMENDOUS;
	}
	
	@Override
	public void setSleepStats() {
		speed = 6;
		weight = Weight.HEAVY;
	}
}
