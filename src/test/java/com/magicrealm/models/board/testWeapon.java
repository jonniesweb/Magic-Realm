package com.magicrealm.models.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Helmet;

public class testWeapon {
	
	@Test
	public void testArmor() {
		MRCharacter amazon = new Amazon();
		
		Armor helmet = amazon.getActiveArmor(Helmet.class);
		assertEquals(true, helmet instanceof Helmet);
	}

}
