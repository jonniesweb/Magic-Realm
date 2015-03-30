package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;

public class Ogre extends MRMonster {

	public Ogre ()
	{
		/* Call inherited constructor *****************************************/
		super ( monster.ogre );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "ogre" );
		setDescription 	 ( "This is a ogre. He is very ugly and smells"      +
						   " particullarly bad. They possess great strength" +
						   " although they maintain little intelligence."    +
						   " When subjected to raw sunlight they will turn"  +
						   " into stone!" );
		setVulnerability ( Weight.MEDIUM );
		setFame			 ( 0 );
		setNotoriety	 ( 2 );
		setMovementSpeed ( 5 );
		
		/* Configure Custom Claw **********************************************/
		Claw claw = ( Claw ) getFirstWeaponOfClass ( Claw.class );
		
		/* Configure the custom update variables */
		claw.setCustomWeightS ( Weight.TREMENDOUS );
		claw.setCustomSpeedS  ( 5 );
		claw.setCustomWeightA ( Weight.HEAVY );
		claw.setCustomSpeedA  ( 5 );
		
		/* Update the default weapon values */
		claw.setSleepStats ();

		/* Configure Custom Tooth *********************************************/
		Tooth tooth = ( Tooth ) getFirstWeaponOfClass ( Tooth.class );
		
		/* Configure the custom update variables */
		tooth.setCustomWeightS ( Weight.TREMENDOUS);
		tooth.setCustomSpeedS  ( 5 );
		tooth.setCustomWeightA ( Weight.HEAVY );
		tooth.setCustomSpeedA  ( 5 );
		
		/* Update the default weapon values */
		tooth.setSleepStats ();	
	
		activateWeapon ( getFirstWeaponOfClass ( Claw.class ) );
	}
	
	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}
	
}
