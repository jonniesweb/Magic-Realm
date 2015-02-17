package com.magicrealm.models;

public class Spear extends Weapon {

	public Spear() {
		super("Spear", Attack.STRIKING, 10, 6, Weight.MEDIUM);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
		speed = 0;
	}

	@Override
	public void setSleepStats() {
		sharpness = 0;
		speed = 6;
	}

}
