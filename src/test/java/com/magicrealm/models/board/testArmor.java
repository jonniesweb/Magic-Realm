package com.magicrealm.models.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Helmet;
import com.magicrealm.models.armors.Armor.Slot;

public class testArmor {
	
	@Test
	public void testArmor() {
		MRCharacter amazon = new Amazon();
		
		Armor helmet = amazon.getActiveArmor(Slot.HELMET);
		assertEquals(true, helmet instanceof Helmet);
	}

}
