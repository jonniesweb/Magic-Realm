package com.magicrealm.models.armors;

import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor.Protection;


public class JadeShield extends Armor {

	protected JadeShield() {
		super("Jade Shield", Slot.SHIELD, Weight.HEAVY, new Protection[]{Protection.ONE});
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
