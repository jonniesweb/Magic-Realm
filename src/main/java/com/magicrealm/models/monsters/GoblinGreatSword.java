package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.GreatSword;

public class GoblinGreatSword extends MRMonster {
	
	public GoblinGreatSword ()
	{
		super ( monster.goblinSword );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes **************************************/
		setName 		 ( "Goblin With Sword" );
		setDescription 	 ( "This peon is a little bit more manageable than" +
						   " it's pesky axe weilding brother. As his sword" +
				           " pretty much compensates for both his wit and"  +
						   " character, one must only mind the first few"   +
				           " initial swings, before the enemy will get"     +
						   " confused and then begin to attack his own"     +
				           " kind. Unusual behaviour, good luck traveller.");
		setVulnerability ( Weight.LIGHT );
		setFame			 ( 1 );
		setNotoriety	 ( 1 );
		setMovementSpeed ( 3 );
		
		GreatSword greatSword = new GreatSword ();
		addWeapon 	   ( greatSword );
		activateWeapon ( greatSword );
	}
	
	@Override
	public String getImageName ()
	{
		return "goblin_sword";
	}

}
