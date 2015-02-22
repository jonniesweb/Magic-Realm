package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Broadsword extends Weapon {

	public Broadsword() {
		super("Broadsword", Attack.STRIKING, 7, 8, Weight.MEDIUM);
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
