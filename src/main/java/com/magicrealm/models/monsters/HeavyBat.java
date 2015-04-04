package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class HeavyBat extends MRMonster {
	
	public HeavyBat ()
	{
		/* Call inherited constructor *****************************************/
		super ( monster.bat );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "Bat" );
		setDescription 	 ( "The Bloods first of only two recourses, when" +
						   " referring to fat kats");
		setVulnerability ( Weight.MEDIUM );
		setFame			 ( 3 );
		setNotoriety	 ( 3 );
		setMovementSpeed ( 2 );
		
		/* Configure Custom Claw **********************************************/
		Claw claw = ( Claw ) getFirstWeaponOfClass ( Claw.class );
		
		/* Configure the custom update variables */
		claw.setCustomWeightS ( Weight.MEDIUM );
		claw.setCustomSpeedS  ( 2 );
		claw.setCustomWeightA ( Weight.MEDIUM );
		claw.setCustomSpeedA  ( 3 );
		
		/* Update the default weapon values */
		claw.setSleepStats ();

		/* Configure Custom Tooth *********************************************/
		Tooth tooth = ( Tooth ) getFirstWeaponOfClass ( Tooth.class );
		
		/* Configure the custom update variables */
		tooth.setCustomWeightS ( Weight.MEDIUM );
		tooth.setCustomSpeedS  ( 2 );
		tooth.setCustomWeightA ( Weight.MEDIUM );
		tooth.setCustomSpeedA  ( 3 );
		
		/* Update the default weapon values */
		tooth.setSleepStats ();
		
		activateWeapon ( getFirstWeaponOfClass ( Claw.class ) );
	}

	@Override
	public String getImageName ()
	{
		return "bat";
	}
}
