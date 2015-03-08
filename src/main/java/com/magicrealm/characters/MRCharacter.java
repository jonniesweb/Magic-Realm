package com.magicrealm.characters;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

import com.magicrealm.activity.Activity;
import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Discoverable;
import com.magicrealm.models.Dwelling;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.Weight;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.utils.GameLog;
import com.magicrealm.utils.ProbabilityCalculator;

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
	
	public enum character { amazon, captain, swordsman };
	public character characterType;
	
	// give 10 to allow character to buy stuff
	protected int gold = 10;
	private dwelling startingLocation;
	protected boolean hidden;
	protected boolean blocked;
	protected Weapon activeWeapon;
	protected Armor activeSuit;
	protected Armor activeHelmet;
	protected Armor activeBreastplate;
	protected Armor activeShield;
	protected ArrayList<Activity> activities;
//	private Person tradingRelationships;
	private ArrayList<Discoverable> discoveries;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private boolean cheatModeEnabled = false;
	
	public MRCharacter(character characterType) {
		this.characterType = characterType;
		fightChits = new ArrayList<ActionChit>();
		moveChits = new ArrayList<ActionChit>();
		activities = new ArrayList<Activity>();
		discoveries = new ArrayList<Discoverable>();
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public int executeActivity() {
		if(activities.size() > 0)
			activities.remove(0).execute(this);
		return activities.size();
	}
	
	public void addDiscovery(Discoverable disc) {
		discoveries.add(disc);
	}
	
	public void attemptHide() {
		if(ProbabilityCalculator.calculateHide())
			this.hide();
		else
			GameLog.log("Character failed to hide");
	}
	
	public void move() {

	}
	
	public void block() {
		blocked = true;
	}
	
	public void rest(ActionChit chit) {
		chit.restoreCharge();
	}
	
	private void hide() {
		GameLog.log("Character Hidden");
		hidden = true;
	}
	
	private void reveal() {
		hidden = false;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public Weapon getActiveWeapon() {
		return activeWeapon;
	}

	public void setActiveWeapon(Weapon activeWeapon) {
		this.activeWeapon = activeWeapon;
		this.activeWeapon.activate();
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
	
	public ActionChit[] getFatiguedChits() {
		ArrayList<ActionChit> chits = new ArrayList<ActionChit>();
		for(ActionChit c: getActionChits()) {
			if(c.getTotalCharges() != c.getCharges() || c.isWounded()) {
				chits.add(c);
			}
		}
		return chits.toArray(new ActionChit[0]);
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

	public boolean isCheatModeEnabled() {
		return cheatModeEnabled;
	}

	public void setCheatModeEnabled(boolean cheatModeEnabled) {
		this.cheatModeEnabled = cheatModeEnabled;
	}
	
	public static MRCharacter createCharacter(character characterType) {
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

	public character getCharacterType() {
		return characterType;
	}

	public dwelling getStartingLocation() {
		return startingLocation;
	}

	public void setStartingLocation(dwelling startingLocation) {
		this.startingLocation = startingLocation;
	}

	public abstract dwelling[] getPossibleStartingLocations();
}
