package com.magicrealm.models;

public class JadeShield extends Armor {

	protected JadeShield() {
		super("Jade Shield", Slot.SHIELD, Weight.HEAVY, Protection.ONE);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 20;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 16;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 10;
	}

}
