package com.magicrealm.models;

public class DevilSword extends Weapon {

	public DevilSword() {
		super("Devil Sword", Attack.STRIKING, 7, 20, Weight.HEAVY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
		speed = 3;
	}

	@Override
	public void setSleepStats() {
		sharpness = 1;
		speed = 4;
	}

}
