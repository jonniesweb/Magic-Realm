package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Dagger extends Weapon {

	public Dagger() {
		super("Dagger", Attack.STRIKING, 0, 0, Weight.NEGLIGIBLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
	}

	@Override
	public void setSleepStats() {
		sharpness = 1;
	}

}
