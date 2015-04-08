package com.magicrealm.models.monsters;

import java.io.Serializable;
import java.util.ArrayList;

import com.magicrealm.models.Placeable;
import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.weapons.Claw;
import com.magicrealm.models.weapons.Tooth;
import com.magicrealm.models.weapons.Weapon;

public abstract class MRMonster implements Serializable, Placeable {
	
	private static final long serialVersionUID = -56577940207485900L;
	
	public enum monster {
		giant, wolf, ogre, ghost, spider, bat, goblinAxe, goblinSword,
		goblinSpear, octopus, viper, dragon, serpent, troll, demon, imp
	};
	
	private monster monsterType;
	
	private boolean attentionChit;
	private String name;
	private String description;
	private Weight vulnerability;
	private int fame;
	private int notoriety;
	private int movementSpeed;
	private int gold = 2;
	private ArrayList<Weapon> weapons;
	private ArrayList<Armor> armors;
	
	protected MRMonster(monster type) {
		monsterType = type;
		
		weapons = new ArrayList<Weapon>();
		armors = new ArrayList<Armor>();
		
		Tooth t = new Tooth();
		addWeapon(new Claw());
		addWeapon(t);
		activateWeapon(t);
	}
	
	public boolean getAttentionChit() {
		return attentionChit;
	}
	
	protected void setAttentionChit(boolean aC) {
		attentionChit = aC;
	}
	
	public String getName() {
		return name;
	}
	
	protected void setName(String n) {
		name = n;
	}
	
	public String getDescription() {
		return description;
	}
	
	protected void setDescription(String d) {
		description = d;
	}
	
	public Weight getVulnerability() {
		return vulnerability;
	}
	
	protected void setVulnerability(Weight v) {
		vulnerability = v;
	}
	
	public int getFame() {
		return fame;
	}
	
	protected void setFame(int f) {
		fame = f;
	}
	
	public int getNotoriety() {
		return notoriety;
	}
	
	protected void setNotoriety(int n) {
		notoriety = n;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	protected void setMovementSpeed(int mSpeed) {
		movementSpeed = mSpeed;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public ArrayList<Weapon> getWeapon() {
		return weapons;
	}
	
	public void addWeapon(Weapon w) {
		weapons.add(w);
	}
	
	public Weapon getWeapon(int i) {
		return weapons.get(i);
	}
	
	public void setWeapon(int i, Weapon w) {
		weapons.set(i, w);
	}
	
	public int findWeapon(Weapon w) {
		return weapons.indexOf(w);
	}
	
	public void activateWeapon(Weapon w) {
		int index = findWeapon(w);
		
		if ((-1) == index) {
			return;
		}
		
		Weapon inactiveWeapon = getWeapon(index);
		Weapon activeWeapon = getActiveWeapon();
		
		if (null != activeWeapon) {
			activeWeapon.sleep();
			activeWeapon.deactivate();
		}
		
		inactiveWeapon.activate();
		inactiveWeapon.sleep();
	}
	
	public Weapon getActiveWeapon() {
		for (Weapon w : getWeapon()) {
			if (w.isActive()) {
				return w;
			}
		}
		
		return null;
	}
	
	public <T extends Weapon> ArrayList<Weapon> getWeaponsOfClass(
			Class<T> wClass) {
		ArrayList<Weapon> wList = new ArrayList<Weapon>();
		
		for (Weapon w : getWeapon()) {
			if (w.getClass() == wClass) {
				wList.add(w);
			}
		}
		
		return wList;
	}
	
	public <T extends Weapon> Weapon getWeaponOfClass(Class<T> wClass, int i) {
		return getWeaponsOfClass(wClass).get(i);
	}
	
	public <T extends Weapon> Weapon getFirstWeaponOfClass(Class<T> wClass) {
		return getWeaponOfClass(wClass, 0);
	}
	
	public ArrayList<Armor> getArmorsAttr() {
		return armors;
	}
	
	public void addArmor(Armor a) {
		armors.add(a);
	}
	
	public Armor getArmor(int i) {
		return armors.get(i);
	}
	
	public int findArmor(Armor a) {
		return armors.indexOf(a);
	}
	
	public void setArmor(int i, Armor a) {
		armors.set(i, a);
	}
	
	public Armor getActiveArmor(Slot s) {
		for (Armor a : getArmorsAttr()) {
			if (a.getSlot() == s && a.isActive()) {
				return a;
			}
		}
		
		return null;
	}
	
	public void activateArmor(Armor a) {
		int index = findArmor(a);
		
		if ((-1) == index) {
			return;
		}
		
		Armor inactiveArmor = getArmor(index);
		Armor activeArmor = getActiveArmor(inactiveArmor.getSlot());
		
		if (null != activeArmor) {
			activeArmor.deactivate();
		}
		
		inactiveArmor.activate();
	}
	
	public monster getMonsterType() {
		return monsterType;
	}
	
	public static MRMonster createMonster(monster type) {
		switch (type) {
		case giant:
			return new Giant();
		case wolf:
			return new Wolf();
		case ogre:
			return new Ogre();
		case ghost:
			return new Ghost();
		case bat:
			return new HeavyBat();
		case spider:
			return new HeavySpider();
		case goblinAxe:
			return new GoblinAxe();
		case goblinSpear:
			return new GoblinSpear();
		case goblinSword:
			return new GoblinGreatSword();
		case octopus:
			return new Octopus();
		case demon:
			return new Demon();
		case imp:
			return new Imp();
		case dragon:
			return new Dragon();
		case viper:
			return new Viper();
		case serpent:
			return new Serpent();
		case troll:
			return new Troll();
			
		default:
			throw new RuntimeException("Invalid monster type");
		}
	}
	
}
