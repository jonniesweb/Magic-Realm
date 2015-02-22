package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class ThrustingSword extends Weapon {

	public ThrustingSword() {
		super("Thrusting Sword", Attack.STRIKING, 4, 6, Weight.LIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
		speed = 0;
	}

	@Override
	public void setSleepStats() {
		sharpness = 1;
		speed = 4;
	}

}
