package com.magicrealm.models;

public class GreatAxe extends Weapon {

	public GreatAxe() {
		super("Great Axe", Attack.STRIKING, 5, 8, Weight.HEAVY);
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
