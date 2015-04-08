package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.GreatSword;

public class GoblinGreatSword extends MRMonster {
	
	public GoblinGreatSword() {
		super(monster.goblinSword);
		
		setAttentionChit(true);
		
		// Set Object Related Attributes
		setName("Goblin With Sword");
		setVulnerability(Weight.LIGHT);
		setFame(1);
		setNotoriety(1);
		setMovementSpeed(3);
		
		GreatSword greatSword = new GreatSword();
		addWeapon(greatSword);
		activateWeapon(greatSword);
	}
	
	@Override
	public String getImageName() {
		return "goblin_sword";
	}
	
}
