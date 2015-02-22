package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class MediumBow extends Weapon {
	
	public MediumBow() {
		super("Medium Bow", Attack.MISSLE, 16, 8, Weight.MEDIUM);
	}

	@Override
	public void setAlertStats() {
		sharpness = 2;
		speed = 1;
	}

	@Override
	public void setSleepStats() {
		sharpness = 0;
		speed = 0;
	}

}
