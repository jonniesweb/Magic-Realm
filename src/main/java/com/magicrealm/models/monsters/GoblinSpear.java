package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Spear;

public class GoblinSpear extends MRMonster {
	
	public GoblinSpear() {
		super(monster.goblinSpear);
		
		setAttentionChit(true);
		
		// Set Object Related Attributes
		setName("Goblin With Spear");
		setVulnerability(Weight.LIGHT);
		setFame(1);
		setNotoriety(1);
		setMovementSpeed(3);
		
		Spear spear = new Spear();
		addWeapon(spear);
		activateWeapon(spear);
	}
	
	@Override
	public String getImageName() {
		return "goblin_spear";
	}
}
