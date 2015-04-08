package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class Wolf extends MRMonster {
	
	public Wolf() {
		// Call inherited constructor
		super(monster.wolf);
		
		setAttentionChit(true);
		
		// Set Object Related Attributes
		setName("Wolf");
		setVulnerability(Weight.MEDIUM);
		setFame(0);
		setNotoriety(1);
		setMovementSpeed(3);
		
		// Configure Custom Claw
		Claw claw = (Claw) getFirstWeaponOfClass(Claw.class);
		
		// Configure the custom update variables
		claw.setCustomWeightS(Weight.MEDIUM);
		claw.setCustomSpeedS(5);
		claw.setCustomWeightA(Weight.LIGHT);
		claw.setCustomSpeedA(3);
		
		// Update the default weapon values
		claw.setSleepStats();
		
		// Configure Custom Tooth
		Tooth tooth = (Tooth) getFirstWeaponOfClass(Tooth.class);
		
		// Configure the custom update variables
		tooth.setCustomWeightS(Weight.MEDIUM);
		tooth.setCustomSpeedS(5);
		tooth.setCustomWeightA(Weight.LIGHT);
		tooth.setCustomSpeedA(3);
		
		// Update the default weapon values
		tooth.setSleepStats();
	}
	
	@Override
	public String getImageName() {
		return "wolf";
	}
}
