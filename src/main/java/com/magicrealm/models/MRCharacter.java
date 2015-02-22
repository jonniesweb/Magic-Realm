package com.magicrealm.models;

import java.awt.Image;
import java.util.ArrayList;

import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.utils.ProbabilityCalculator;

public abstract class MRCharacter implements Placeable {
	
	protected String name;
	protected Image image;
	protected String description;
	protected Weight vulnerability;
	protected boolean attentionChit;
	protected int fame;
	protected int notoriety;
	protected ArrayList<ActionChit> fightChits;
	protected ArrayList<ActionChit> moveChits;
	protected int gold;
	protected Dwelling startingLocation;
	protected boolean hidden;
	protected boolean blocked;
	protected Weapon activeWeapon;
	protected Armor activeSuit;
	protected Armor activeHelmet;
	protected Armor activeBreastplate;
	protected Armor activeShield;
	protected ArrayList<Activity> activities;
//	private Person tradingRelationships;
//	private int discoveries;
	
	public MRCharacter() {
		fightChits = new ArrayList<ActionChit>();
		moveChits = new ArrayList<ActionChit>();
		activities = new ArrayList<Activity>();
	}
	
	public void addActivity(Activity activity) {
		System.out.println(activity.toString());
		activities.add(activity);
	}
	
	public int executeActivity() {
		activities.remove(0).execute(this);
		return activities.size();
	}
	
	public void attemptHide() {
		if(ProbabilityCalculator.calculateHide())
			this.hide();
	}
	
	public void attemptSearchTreasure() {
		if(ProbabilityCalculator.findTreasure()) {
			//find some treasure
		}
	}
	
	public void attemptSearchPassage() {
		if(ProbabilityCalculator.findPassage()) {
			//find passage
		}
	}
	
	public void move() {

	}
	
	public void block() {
		blocked = true;
	}
	
	public void rest() {
		
	}
	
	private void hide() {
		System.out.println("Character Hidden");
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
	
}
