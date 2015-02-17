package com.magicrealm.models;

public class TruesteelSword extends Weapon {

	public TruesteelSword() {
		super("Truesteel Sword", Attack.STRIKING, 7, 25, Weight.MEDIUM);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 2;
	}

	@Override
	public void setSleepStats() {
		sharpness = 2;
	}

}
