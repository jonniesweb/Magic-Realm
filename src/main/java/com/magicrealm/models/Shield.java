package com.magicrealm.models;

public class Shield extends Armor {

	protected Shield() {
		super("Shield", Slot.SHIELD, Weight.MEDIUM, Protection.ONE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 7;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 5;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 0;
	}

}