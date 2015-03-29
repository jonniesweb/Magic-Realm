package com.magicrealm.models.monsters;

import com.magicrealm.models.Weight;

public class Wolf extends MRMonster {

	public Wolf ()
	{
		/* Call inherited constructor */
		super ( monster.wolf );
		
		setAttentionChit ( true );
		
		/* Set Object Related Attributes */
		setName 		 ( "wolf" );
		setDescription 	 ( "This is a wolf. He likes red meat and red capes." +
							" Watch out Little Red Riding Hood." );
		setVulnerability ( Weight.MEDIUM );
		setFame			 ( 0 );
		setNotoriety	 ( 1 );
	}
	
	@Override
	public String getImageName ()
	{
		return "theNameOfThisImageFile";
	}
}
