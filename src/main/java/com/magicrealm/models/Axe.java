package com.magicrealm.models;

public class Axe extends Weapon {

	public Axe() {
		super("Axe", Attack.STRIKING, 2, 4, Weight.MEDIUM);
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
		speed = 5;
	}

}
