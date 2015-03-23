package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor.Protection;

public class GoldHelmet extends Armor {

	protected GoldHelmet() {
		super("Gold Helmet", Slot.HELMET, Weight.HEAVY, new Protection[]{Protection.SMASH});
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
