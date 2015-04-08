package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class HeavySpider extends MRMonster {
	
	public HeavySpider() {
		// Call inherited constructor
		super(monster.spider);
		
		setAttentionChit(true);
		
		// Set Object Related Attributes
		setName("Spider");
		setVulnerability(Weight.LIGHT);
		setFame(3);
		setNotoriety(3);
		setMovementSpeed(4);
		
		// Configure Custom tooth
		Claw claw = (Claw) getFirstWeaponOfClass(Claw.class);
		
		// Configure the custom update variables
		claw.setCustomWeightS(Weight.LIGHT);
		claw.setCustomSpeedS(6);
		claw.setCustomWeightA(Weight.TREMENDOUS);
		claw.setCustomSpeedA(4);
		
		// Update the default weapon values
		claw.setSleepStats();
		
		// Configure Custom Tooth
		Tooth tooth = (Tooth) getFirstWeaponOfClass(Tooth.class);
		
		// Configure the custom update variables */
		tooth.setCustomWeightS(Weight.LIGHT);
		tooth.setCustomSpeedS(6);
		tooth.setCustomWeightA(Weight.TREMENDOUS);
		tooth.setCustomSpeedA(4);
		
		// Update the default weapon values
		tooth.setSleepStats();
		
		activateWeapon(getFirstWeaponOfClass(Tooth.class));
	}
	
	@Override
	public String getImageName() {
		return "spider";
	}
}
