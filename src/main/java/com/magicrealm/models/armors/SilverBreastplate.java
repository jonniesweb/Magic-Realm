package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;

public class SilverBreastplate extends Armor {

	protected SilverBreastplate() {
		super("Silver Breastplate", Slot.BREASTPLATE, Weight.HEAVY, Protection.SWING);
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
		price = 21;
	}

	@Override
	public void setDestroyedPrice() {
		// TODO Auto-generated method stub
		price = 15;
	}

}
