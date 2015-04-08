package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Axe;

public class GoblinAxe extends MRMonster {
	
	public GoblinAxe() {
		super(monster.goblinAxe);
		
		setAttentionChit(true);
		
		// Set Object Related Attributes
		setName("Goblin With Axe");
		setVulnerability(Weight.LIGHT);
		setFame(1);
		setNotoriety(1);
		setMovementSpeed(3);
		
		Axe axe = new Axe();
		addWeapon(axe);
		activateWeapon(axe);
	}
	
	@Override
	public String getImageName() {
		return "goblin_axe";
	}
}
