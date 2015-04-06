package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.monsters.MRMonster.monster;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class Octopus extends MRMonster {

	public Octopus ()
	{
		/* Call inherited constructor *****************************************/
		super ( monster.octopus );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "octopus" );
		setDescription 	 ( "Beware the water, ye' landlubber!" );
		setVulnerability ( Weight.MEDIUM );
		setFame			 ( 8 );
		setNotoriety	 ( 8 );
		setMovementSpeed ( 2 );
		
		/* Configure Custom Claw **********************************************/
		Claw claw = ( Claw ) getFirstWeaponOfClass ( Claw.class );
		
		/* Configure the custom update variables */
		claw.setCustomWeightS ( Weight.LIGHT );
		claw.setCustomSpeedS  ( 4 );
		claw.setCustomWeightA ( Weight.LIGHT );
		claw.setCustomSpeedA  ( 2 );
		
		/* Update the default weapon values */
		claw.setSleepStats ();

		/* Configure Custom Tooth *********************************************/
		Tooth tooth = ( Tooth ) getFirstWeaponOfClass ( Tooth.class );
		
		/* Configure the custom update variables */
		tooth.setCustomWeightS ( Weight.LIGHT );
		tooth.setCustomSpeedS  ( 4 );
		tooth.setCustomWeightA ( Weight.LIGHT );
		tooth.setCustomSpeedA  ( 2 );
		
		/* Update the default weapon values */
		tooth.setSleepStats ();
		
		activateWeapon ( getFirstWeaponOfClass ( Claw.class ) );

	}
	
	@Override
	public String getImageName ()
	{
		return "theNameofThisImageFile";
	}
}
