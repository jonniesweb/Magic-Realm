package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.utils.ProbabilityCalculator;

public class Character {
	
	private String name;
	private Image picture;
	private String description;
	public enum vulnerability {light, medium, heavy};
	private boolean attentionChit;
	private int fame;
	private int notoriety;
	private ActionChit actionChit;
	private int gold;
	private Dwelling startingLocation;
	private boolean isHidden;
//	private Person tradingRelationships;
//	private int discoveries;
	
	public Character() {
		
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
	
	private void hide() {
		isHidden = true;
	}
}
