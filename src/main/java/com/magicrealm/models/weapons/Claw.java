package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Claw extends MonsterUnarmed {

	public Claw ()
	{
		super (	"Claw",				/* Name		*/
				Attack.STRIKING,	/* Attack	*/
				0,					/* Length	*/
				1,					/* Price	*/
				Weight.MEDIUM );	/* Weight	*/
	}
	
}
