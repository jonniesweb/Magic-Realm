package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class LivingSword extends Weapon {

	public LivingSword() {
		super("Living Sword", Attack.STRIKING, 4, 25, Weight.LIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 2;
		speed = 2;
	}

	@Override
	public void setSleepStats() {
		sharpness = 2;
		speed = 3;
	}

}
