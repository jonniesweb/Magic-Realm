package com.magicrealm.models;

public class GreatSword extends Weapon {

	public GreatSword() {
		super("Great Sword", Attack.STRIKING, 8, 10, Weight.HEAVY);
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
		speed = 6;
	}

}
