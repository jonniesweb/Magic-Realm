package com.magicrealm.models;

public class LightBow extends Weapon {
	
	public LightBow() {
		super("Light Bow", Attack.MISSLE, 14, 6, Weight.LIGHT);
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
