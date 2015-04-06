package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Spear;

public class GoblinwSpear extends MRMonster {

	public GoblinwSpear ()
	{
		super ( monster.goblinwSpear );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "goblinwSpear" );
		setDescription 	 ( "This feller will gut you like a fish!!!" 		+
						   " Watch out, this crazed offshoot of gangrene" 	+
						   " utilizes many different types of spears." );
		setVulnerability ( Weight.LIGHT );
		setFame			 ( 1 );
		setNotoriety	 ( 1 );
		setMovementSpeed ( 3 );
		
		Spear spear = new Spear ();
		addWeapon 	   ( spear );
		activateWeapon ( spear );
	}
	
	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}
}
