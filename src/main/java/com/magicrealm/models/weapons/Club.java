package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public class Club extends Weapon {

	public Club ()
	{
		super (	"Club",				/* Name		*/
				Attack.STRIKING,	/* Attack	*/
				8,					/* Length	*/
				2,					/* Price 	*/
				Weight.HEAVY		/* Weight 	*/
			   );
	}

	@Override
	public void setAlertStats () {
		speed	  = 4;
		weight	  = Weight.TREMENDOUS;
	}

	@Override
	public void setSleepStats () {
		speed	  = 6;
		weight	  = Weight.HEAVY;
	}
}
