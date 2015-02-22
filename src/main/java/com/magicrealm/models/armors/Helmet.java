package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;

public class Helmet extends Armor {

	protected Helmet() {
		super("Helmet", Slot.HELMET, Weight.MEDIUM, Protection.SMASH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 5;
		
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 3;
		state = State.DAMAGED;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		state = State.DESTROYED;
	}

}
