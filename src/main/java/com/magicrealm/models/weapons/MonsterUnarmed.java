package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public abstract class MonsterUnarmed extends Weapon {
	
	/* Variables **************************************************************/
	/* These are custom attribute additions made to the weapon class,         */
	/* in order to handle monster variants with different |weight| and        */
	/* |speed| stats.                                                         */
	protected int	 customSpeedS;
	protected Weight customWeightS;
	protected int	 customSpeedA;
	protected Weight customWeightA;
	
	
	/* Methods ****************************************************************/
	
	/* MONSTERUNARMED INTERFACE FUNCTIONS *************************************/
	public MonsterUnarmed (	String	name,
							Attack	attack,
							int		length,
							int		price,
							Weight	weight )
	{
		super ( name, attack, length, price, weight );
		
		setCustomSpeedA  ( speed );
		setCustomSpeedS  ( speed );
		setCustomWeightA ( weight );
		setCustomWeightS ( weight );
	}
	
	@Override
	public void setAlertStats ()
	{
		speed  = customSpeedA;
		weight = customWeightA;
	}
	
	@Override
	public void setSleepStats ()
	{
		speed  = customSpeedS;
		weight = customWeightS;
	}
	/* END OF MONSTERUNARMED INTERFACE FUNCTIONS ******************************/
	
	/* MONSTERUNARMED CUSTOM ATTRIBUTES - GET/SET *****************************/
	public void setCustomSpeedA ( int s )
	{
		customSpeedA = s;
	}
	public void setCustomWeightA ( Weight w )
	{
		customWeightA = w;
	}
	public void setCustomSpeedS ( int s )
	{
		customSpeedS = s;
	}
	public void setCustomWeightS ( Weight w )
	{
		customWeightS = w;
	}
	/* END OF MONSTERUNARMED CUSTOM ATTRIBUTES - GET/SET **********************/
}
