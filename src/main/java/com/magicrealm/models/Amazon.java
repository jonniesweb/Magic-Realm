package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.GameState;
import com.magicrealm.utils.ImageCache;

public class Amazon extends MRCharacter {
	
	public Amazon() {
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		setActiveWeapon(new ShortSword());
//		startingLocation = 
	}
	
	public void setupActionChits() {
		
		//fight chits
		fightChits.add(new ActionChit(Weight.HEAVY, ActionChit.Action.FIGHT, 4, 3));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 5, 0));
		
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 3, 4));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 4, 2));	
		
		//move chits
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 3, 3));
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 4, 1));
	}

	@Override
	public Image getImage() {
		return ImageCache.getImage("amazon");
	}

	@Override
	public String getImageName() {
		return "amazon";
	}

}
