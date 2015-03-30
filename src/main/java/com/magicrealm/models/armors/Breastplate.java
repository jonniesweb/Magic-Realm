package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;

public class Breastplate extends Armor {

	public Breastplate() {
		super("Breastplate", Slot.BREASTPLATE, Weight.MEDIUM, new Protection[]{Protection.SWING, Protection.THRUST});
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setIntactPrice() {
		// TODO Auto-generated method stub
		price = 9;
	}

	@Override
	public void setDamagedPrice() {
		// TODO Auto-generated method stub
		price = 6;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 0;
	}

}
