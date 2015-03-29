package com.magicrealm.models.monsters;

/* Java Standard APIs */
import java.io.Serializable;
import java.util.ArrayList;

/* Project Specific Imports */
import com.magicrealm.models.Placeable;
import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;


public abstract class MRMonster implements Serializable, Placeable {

	/* Variables *//***********************************************************/
	
	/* Object Related References **********************************************/
	/* PURPOSE: these are variables related with inter-object communication.  */
	/*			They can be used by methods to determine the monster type     */
	/*       	associated with the serialized UID. This set of variables     */
	/*			support identification and type of the object.				  */
	/**************************************************************************/
	private static	final long 	serialVersionUID = -56577940207485900L;
	public	enum	monster { giant, wolf };
	public	monster	monsterType;


	/* Control Related Attributes *********************************************/
	/* PURPOSE: these are values related to the game's board state.           */
	/**************************************************************************/
	private	boolean attentionChit;	
	
	/* Object Related Attributes **********************************************/
	/* PURPOSE: these are variables that facilitate in-game interaction       */
	/*			between the game's board, other denizens and this object      */
	/*        	itself.												          */
	/**************************************************************************/
	private String 					name;
	private String 					description;
	private Weight					vulnerability;
	private int 					fame;
	private int 					notoriety;
	private ArrayList	<Weapon>	weapons;			
	private ArrayList	<Armor>		armors;				
		
	/* Methods *//*************************************************************/
	
	/* CONTROL RELATED ATTRIBUTES - GET/SET ***********************************/
	/* PURPOSE:	These are get and set functions for the basic Control Related */
	/*			Attribute data-types.										  */
	/**************************************************************************/
	public boolean getAttentionChit ()
	{
		return attentionChit;
	}
	protected void setAttentionChit ( boolean aC )
	{
		attentionChit = aC;
	}
	/* END OF CONTROL RELATED ATTRIBUTES - GET/SET ****************************/
	
	/* OBJECT RELATED ATTRIBUTES - GET/SET ************************************/
	/* PURPOSE:	These are get and set functions for the basic Object Related  */
	/*			attribute data-types.										  */
	/**************************************************************************/
	public String getName ()
	{
		return name;
	}
	protected void setName ( String n )
	{
		name = n;
	}
	public String getDescription ()
	{
		return description;
	}
	protected void setDescription ( String d )
	{
		description = d;
	}
	public Weight getVulnerability ()
	{
		return vulnerability;
	}
	protected void setVulnerability ( Weight v )
	{
		vulnerability = v;
	}
	public int getFame ()
	{
		return fame;
	}
	protected void setFame ( int f )
	{
		fame = f;
	}
	public int getNotoriety ()
	{
		return notoriety;
	}
	protected void setNotoriety ( int n )
	{
		notoriety = n;
	}
	/* END OF OBJECT RELATED ATTRIBUTES - GET/SET *****************************/
	
	/* WEAPONS*****************************************************************/
	/* PURPOSE:	Weapon related storage access methods. There are two types of */
	/*			storage access methods: core functions and manipulators.      */
	/*			Core functions are implemented to access raw data attributes  */
	/*			and their pre-written APIs. On the other hand, manipulators'  */
	/*			actions are restricted to the use of these core functions and */
	/*   		unrelated object oriented constructs. By maintaining this     */
	/*			degree of separation, and hence abstraction, the underlying   */
	/*			variables can be replaced, added to or modified very easily.  */
	/**************************************************************************/
	
	/* Core Functions *********************************************************/
	public ArrayList<Weapon> getWeaponsAttr ()
	{
		return weapons;
	}
	public void addWeapon ( Weapon w )
	{
		weapons.add( w );
	}
	public Weapon getWeapon ( int i )
	{
		return weapons.get ( i );
	}
	public void setWeapon ( int i, Weapon w )
	{
		weapons.set( i, w );
	}
	public int findWeapon ( Weapon w )
	{
		return weapons.indexOf ( w );
	}
	
	/* Manipulator Functions **************************************************/
	public void activateWeapon ( Weapon w )
	{
		int index = findWeapon ( w );
		
		if ( (-1) == index )
		{
			return;
		}
		
		Weapon inactiveWeapon = getWeapon 		( index );
		Weapon activeWeapon   = getActiveWeapon ();
		
		if ( null != activeWeapon )
		{
			activeWeapon.sleep ();
			activeWeapon.deactivate ();
		}
		
		inactiveWeapon.activate ();
		inactiveWeapon.sleep ();	
	}
	public Weapon getActiveWeapon ()
	{
		for ( Weapon w : getWeaponsAttr () )
		{
			if ( w.isActive() )
			{
				return w;
			}
		}
		
		return null;
	}
	/* END OF WEAPONS *********************************************************/
	
	/* ARMOR ******************************************************************/
	/* PURPOSE:	Armor related storage access methods. There are two types of  */
	/*			storage access methods: core functions and manipulators.      */
	/*			Core functions are implemented to access raw data attributes  */
	/*			and their pre-written APIs. On the other hand, manipulators'  */
	/*			actions are restricted to the use of these core functions and */
	/*   		unrelated object oriented constructs. By maintaining this     */
	/*			degree of separation, and hence abstraction, the underlying   */
	/*			variables can be replaced, added to or modified very easily.  */
	/**************************************************************************/
	
	/* Core Functions *********************************************************/
	public ArrayList<Armor> getArmorsAttr ()
	{
		return armors;
	}
	public void addArmor ( Armor a )
	{
		armors.add( a );
	}
	public Armor getArmor ( int i )
	{
		return armors.get ( i );
	}
	public int findArmor ( Armor a )
	{
		return armors.indexOf ( a );
	}
	public void setArmor ( int i, Armor a )
	{
		armors.set( i, a );
	}
	
	/* Manipulator Functions **************************************************/
	public Armor getActiveArmor ( Slot s )
	{
		for ( Armor a : getArmorsAttr () )
		{
			if ( 	a.getSlot  () == s &&
					a.isActive () 			)
			{
				return a;
			}
		}
		
		return null;
	}
	
	public void activateArmor ( Armor a )
	{
		int index = findArmor ( a );
		
		if ( (-1) == index )
		{
			return;
		}
		
		Armor inactiveArmor = getArmor 		 ( index );
		Armor activeArmor 	= getActiveArmor ( inactiveArmor.getSlot () );
		
		if ( null != activeArmor )
		{
			activeArmor.deactivate ();
		}
		
		inactiveArmor.activate ();			
	}
	
	/* END OF ARMOR ***********************************************************/
	
	/* REFERENCE INFORMATION **************************************************/
	/* PURPOSE:	these methods provide information about this monster type.    */
	/**************************************************************************/
	public monster getMonsterType ()
	{
		return monsterType;
	}
	/* END OF REFERENCE INFORMATION *******************************************/
	
	/* DYNAMIC INSTANTIATION **************************************************/
	/* PURPOSE:	these methods are responsible for instantiating  monster      */
	/*			types.														  */
	/**************************************************************************/
	public static MRMonster createMonster (monster type)
	{
		switch ( type )
		{
			case giant:
				return ( new Giant () );
			case wolf:
				return ( new Wolf () );
				
			default:
				throw new RuntimeException ( "Invalid monster type" );
		}
	}
	/* END OF DYNAMIC INSTANTIATION *******************************************/
	
	public MRMonster ( monster type )
	{
		/* Define Type */
		monsterType = type;
		
		/* Initialize memory */
		weapons	= new ArrayList<Weapon>();
		armors	= new ArrayList<Armor>();
		
		/* Assign Defaults */
		Claw  c = new Claw ();
		Tooth t	= new Tooth ();
		addWeapon ( c );
		addWeapon ( t );	
		activateWeapon ( t );
	}
		
}
