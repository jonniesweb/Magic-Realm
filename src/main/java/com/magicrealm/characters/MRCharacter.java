package com.magicrealm.characters;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

import com.magicrealm.activity.Activity;
import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Discoverable;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.server.ServerGameState;

public abstract class MRCharacter implements Serializable, Placeable {
	
	private static final long serialVersionUID = 5714651037717652242L;
	protected String name;
	protected String description;
	protected Weight vulnerability;
	protected boolean attentionChit;
	protected int fame;
	protected int notoriety;
	protected ArrayList<ActionChit> fightChits;
	protected ArrayList<ActionChit> moveChits;
	
	public enum CharacterType { amazon, captain, swordsman };
	public CharacterType characterType;
	
	// give 10 to allow character to buy stuff
	protected int gold = 10;
	protected int health = 2;
	private dwelling startingLocation;
	protected boolean hidden = false;
	protected boolean blocked;
	protected ArrayList<Weapon> weapons;
	protected ArrayList<Armor> armors;
	protected ArrayList<Activity> activities;

	private ArrayList<Discoverable> discoveries;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private boolean cheatModeEnabled = false;
	private boolean hiddenEnemiesFound;
	
	public MRCharacter(CharacterType characterType) {
		this.characterType = characterType;
		fightChits = new ArrayList<ActionChit>();
		moveChits = new ArrayList<ActionChit>();
		activities = new ArrayList<Activity>();
		discoveries = new ArrayList<Discoverable>();
		weapons = new ArrayList<Weapon>();
		armors = new ArrayList<Armor>();
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void addDiscovery(Discoverable disc) {
		discoveries.add(disc);
	}
	
	public void block() {
		blocked = true;
	}
	
	public void rest(ActionChit chit) {
		chit.restoreCharge();
	}
	
	public void hide() {
		hidden = true;
	}
	
	public void reveal() {
		hidden = false;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public Weapon getActiveWeapon() {
		for (Weapon weapon : weapons) {
			if(weapon.isActive())
				return weapon;
		}
		return null;
	}

	public void activateWeapon(Weapon activeWeapon) {
		int i = weapons.indexOf(activeWeapon);
		
		if(i == -1)
			return;
		
		Weapon newWep = weapons.get(i);

		Weapon oldWep = getActiveWeapon();
		if(oldWep != null) {
			oldWep.sleep();
			oldWep.deactivate();
		}
		newWep.activate();
		newWep.sleep();
	}
	
	public Armor getActiveArmor(Slot s) {
		for (Armor armor : armors) {
			if(armor.isActive() && armor.getSlot() == s)
				return armor;
		}
		return null;
	}
	
	public void activateArmor(Armor activeArmor) {
		int i = armors.indexOf(activeArmor);
		
		if(i == -1)
			return;
		
		Armor newArm = armors.get(i);
		
		Armor oldArm = getActiveArmor(newArm.getSlot());
		if(oldArm != null) {
			oldArm.deactivate();
		}
		newArm.activate();
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public ArrayList<Armor> getArmors() {
		return armors;
	}

	public ArrayList<ActionChit> getFightChits() {
		return fightChits;
	}

	public ArrayList<ActionChit> getMoveChits() {
		return moveChits;
	}
	
	public ArrayList<ActionChit> getActionChits() {
		ArrayList<ActionChit> all = new ArrayList<ActionChit>();
		all.addAll(moveChits);
		all.addAll(fightChits);
		return all;
	}
	
	public ArrayList<ActionChit> getNonWoundedActionChits() {
		ArrayList<ActionChit> chits = new ArrayList<ActionChit>();
		for(ActionChit c: getActionChits()) {
			if(!c.isWounded()) {
				chits.add(c);
			}
		}
		return chits;
	}
	
	public ActionChit[] getFatiguedChits() {
		ArrayList<ActionChit> chits = new ArrayList<ActionChit>();
		for(ActionChit c: getActionChits()) {
			if(c.getTotalCharges() != c.getCharges() || c.isWounded()) {
				chits.add(c);
			}
		}
		return chits.toArray(new ActionChit[0]);
	}
	
	public void fatigueChit(ActionChit ac) {
		getActionChits().get(getActionChits().indexOf(ac)).fatigue();
	}
	
	public ArrayList<Discoverable> getDiscoveries() {
		return discoveries;
	}

	public String getName() {
		return name;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		int old = this.gold;
		this.gold = gold;
		pcs.firePropertyChange("gold", old, gold);
		
	}
	
	public PropertyChangeSupport getPropertyChangeSupport() {
		return pcs;
	}

	
	/**
	 * @return
	 * @deprecated Use {@link ServerGameState#isCheatMode()} instead
	 */
	public boolean isCheatModeEnabled() {
		return cheatModeEnabled;
	}

	public void setCheatModeEnabled(boolean cheatModeEnabled) {
		this.cheatModeEnabled = cheatModeEnabled;
	}
	
	public static MRCharacter createCharacter(CharacterType characterType) {
		switch (characterType) {
		case amazon:
			return new Amazon();
		case captain:
			return new Captain();
		case swordsman:
			return new Swordsman();
		
		default:
			throw new RuntimeException("Invalid character name");
		}
	}

	public CharacterType getCharacterType() {
		return characterType;
	}

	public dwelling getStartingLocation() {
		return startingLocation;
	}

	public void setStartingLocation(dwelling startingLocation) {
		this.startingLocation = startingLocation;
	}

	public abstract dwelling[] getPossibleStartingLocations();
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MRCharacter) {
			return characterType.equals(((MRCharacter) obj).getCharacterType());
		} else
			return false;
	}

	public boolean isHidden() {
		return hidden;
	}

	public boolean isHiddenEnemiesFound() {
		return hiddenEnemiesFound;
	}

	public void setHiddenEnemiesFound(boolean hiddenEnemiesFound) {
		this.hiddenEnemiesFound = hiddenEnemiesFound;
	}

	public Weight getVulnerability() {
		return vulnerability;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void increaseHealth(int increase) {
		this.health = Math.min(health + increase, 10);
	}
	
	public void decreaseHealth(int decrease) {
		this.health = Math.max(health - decrease, 0);
	}

}
