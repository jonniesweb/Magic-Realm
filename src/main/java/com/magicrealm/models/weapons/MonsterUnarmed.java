package com.magicrealm.models.weapons;

import com.magicrealm.models.Weight;

public abstract class MonsterUnarmed extends Weapon {
	
	public MonsterUnarmed (	String	name,
							Attack	attack,
							int		length,
							int		price,
							Weight	weight )
	{
		super ( name, attack, length, price, weight );
	}
	
	@Override
	public void setAlertStats ()
	{
		/* Not a feature supported by this class */
	}
	
	@Override
	public void setSleepStats ()
	{
		/* Not a feature supported by this class */
	}
}
