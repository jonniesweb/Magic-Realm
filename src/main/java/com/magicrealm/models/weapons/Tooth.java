package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Tooth extends MonsterUnarmed {

	public Tooth ()
	{
		super (	"Tooth",			/* Name		*/
				Attack.STRIKING,	/* Attack	*/
				0,					/* Length	*/
				1,					/* Price	*/
				Weight.MEDIUM );	/* Weight	*/
	}
}
