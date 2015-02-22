package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;

public class Suit extends Armor {

	protected Suit() {
		super("Suit of Armor", Slot.SUIT, Weight.HEAVY, Protection.ALL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 17;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 12;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 0;
	}

}
