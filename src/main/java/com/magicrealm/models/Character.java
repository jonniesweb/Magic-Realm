package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.utils.ProbabilityCalculator;

public abstract class Character {
	
	protected String name;
	protected Image picture;
	protected String description;
	protected Weight vulnerability;
	protected boolean attentionChit;
	protected int fame;
	protected int notoriety;
	protected ActionChit[] actionChits;
	protected int gold;
	protected Dwelling startingLocation;
	protected boolean hidden;
	protected boolean blocked;
	protected Weapon activeWeapon;
	protected Armor activeSuit;
	protected Armor activeHelmet;
	protected Armor activeBreastplate;
	protected Armor activeShield;
//	private Person tradingRelationships;
//	private int discoveries;
	
	//activities
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
	
	private void hide() {
		hidden = true;
	}
}
