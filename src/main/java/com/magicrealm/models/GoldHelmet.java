package com.magicrealm.models;

public class GoldHelmet extends Armor {

	protected GoldHelmet() {
		super("Gold Helmet", Slot.HELMET, Weight.HEAVY, Protection.SMASH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 30;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 27;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 20;
	}

}
