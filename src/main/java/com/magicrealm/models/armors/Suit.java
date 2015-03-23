package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor.Protection;

public class Suit extends Armor {

	protected Suit() {
		super("Suit of Armor", Slot.SUIT, Weight.HEAVY, new Protection[]{Protection.SMASH, Protection.SWING, Protection.THRUST});
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
