package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;

public class Ghost extends MRMonster {

	public Ghost ()
	{
		/* Call inherited constructor */
		super ( monster.ghost );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes */
		setName 		 ( "ghost" );
		setDescription 	 ( "This is a ghost. Oooooooh run away!" );
		setVulnerability ( Weight.MEDIUM );
		setFame			 ( 0 );
		setNotoriety	 ( 2 );
		
		activateWeapon ( getFirstWeaponOfClass ( Claw.class ) );
	}
	
	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}
}
