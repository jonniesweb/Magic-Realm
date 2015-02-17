package com.magicrealm.models;

public class BaneSword extends Weapon {

	public BaneSword() {
		super("Bane Sword", Attack.STRIKING, 8, 20, Weight.TREMENDOUS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
		speed = 2;
	}

	@Override
	public void setSleepStats() {
		sharpness = 1;
		speed = 8;
	}

}
