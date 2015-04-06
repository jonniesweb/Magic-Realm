package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Club;

public class Giant extends MRMonster {

	public Giant ()
	{
		/* Call inherited constructor *****************************************/
		super ( monster.giant );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "giant" );
		setDescription 	 ( "This is a giant. He is tremendous. But he took" +
						   " an arrow to the knee. Don't worry the adoring" +
						   " fan has your back. I'm on crack." );
		setVulnerability ( Weight.TREMENDOUS );
		setFame			 ( 8 );
		setNotoriety	 ( 8 );
		setMovementSpeed ( 5 );
		
		Club club = new Club ();
		addWeapon 	   ( club );
		activateWeapon ( club );
	}

	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}

}
