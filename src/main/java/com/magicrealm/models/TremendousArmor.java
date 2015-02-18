package com.magicrealm.models;

public class TremendousArmor extends Armor {

	protected TremendousArmor() {
		super("Tremendous Armor", Slot.SUIT, Weight.TREMENDOUS, Protection.ALL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 25;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 18;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 5;
	}

}
