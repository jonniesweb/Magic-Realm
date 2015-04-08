package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class Ghost extends MRMonster {
	
	public Ghost() {
		super(monster.ghost);
		
		setAttentionChit(true);
		
		setName("Ghost");
		setDescription("This is a ghost. Ooooooo run away!");
		setVulnerability(Weight.MEDIUM);
		setFame(0);
		setNotoriety(2);
		setMovementSpeed(4);
		
		// Configure Custom Claw
		Claw claw = (Claw) getFirstWeaponOfClass(Claw.class);
		
		// Configure the custom update variables
		claw.setCustomWeightS(Weight.HEAVY);
		claw.setCustomSpeedS(4);
		claw.setCustomWeightA(Weight.LIGHT);
		claw.setCustomSpeedA(2);
		
		// Update the default weapon values
		claw.setSleepStats();
		
		// Configure Custom Tooth
		Tooth tooth = (Tooth) getFirstWeaponOfClass(Tooth.class);
		
		// Configure the custom update variables
		tooth.setCustomWeightS(Weight.HEAVY);
		tooth.setCustomSpeedS(4);
		tooth.setCustomWeightA(Weight.LIGHT);
		tooth.setCustomSpeedA(2);
		
		// Update the default weapon values
		tooth.setSleepStats();
		
		activateWeapon(getFirstWeaponOfClass(Claw.class));
	}
	
	@Override
	public String getImageName() {
		return "ghost";
	}
}
