package com.magicrealm.models;

public class MorningStar extends Weapon {

	public MorningStar() {
		super("Morning Star", Attack.STRIKING, 6, 8, Weight.HEAVY);
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
