package com.magicrealm.models;

public class Mace extends Weapon {

	public Mace() {
		super("Mace", Attack.STRIKING, 1, 6, Weight.MEDIUM);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		speed = 3;
	}

	@Override
	public void setSleepStats() {
		speed = 0;
	}

}
