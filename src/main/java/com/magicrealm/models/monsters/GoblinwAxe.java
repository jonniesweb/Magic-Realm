package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.Axe;

public class GoblinwAxe extends MRMonster {

	public GoblinwAxe ()
	{
		super ( monster.goblinwAxe );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "goblinwAxe" );
		setDescription 	 ( "This is a dangerous enemy, being of a size lower" +
						   " than your waist, these tricky vermin always"     +
						   " operate below the belt. Be even more wary, as"   +
						   " this one fancies axes and the squelching sounds" +
						   " they produce upon splitting skulls.");
		setVulnerability ( Weight.LIGHT );
		setFame			 ( 1 );
		setNotoriety	 ( 1 );
		setMovementSpeed ( 3 );
		
		Axe axe = new Axe ();
		addWeapon 	   ( axe );
		activateWeapon ( axe );
	}
	
	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}
}
