package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class CrossBow extends Weapon {

	public CrossBow() {
		super("CrossBow", Attack.MISSLE, 12, 10, Weight.HEAVY);
	}

	@Override
	public void setAlertStats() {
		sharpness = 1;
		speed = 1;
	}

	@Override
	public void setSleepStats() {
		sharpness = 0;
		speed = 0;
	}

}
