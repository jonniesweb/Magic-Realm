package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class ShortSword extends Weapon {
	
	public ShortSword() {
		super("Short sword", Attack.STRIKING, 3, 4, Weight.LIGHT);
		
	}

	@Override
	public void setAlertStats() {
		// TODO Auto-generated method stub
		sharpness = 1;
		speed = 0;
	}

	@Override
	public void setSleepStats() {
		// TODO Auto-generated method stub
		sharpness = 1;
		speed = 0;
	}	
	

}
