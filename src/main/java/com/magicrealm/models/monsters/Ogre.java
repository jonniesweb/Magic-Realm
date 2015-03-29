package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Claw;

public class Ogre extends MRMonster {

	public Ogre ()
	{
		/* Call inherited constructor */
		super ( monster.ogre );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes */
		setName 		 ( "ogre" );
		setDescription 	 ( "This is a ogre. He is very ugly and smells"      +
						   " particullarly bad. They possess great strength" +
						   " although they maintain little intelligence."    +
						   " When subjected to raw sunlight they will turn"  +
						   " into stone!" );
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
