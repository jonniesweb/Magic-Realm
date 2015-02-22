package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.models.weapons.ShortSword;
import com.magicrealm.utils.ImageCache;

public class Amazon extends MRCharacter {
	
	public Amazon() {
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		setActiveWeapon(new ShortSword());
//		startingLocation =
		setupActionChits();
	}
	
	public void setupActionChits() {
		
		//fight chits
		fightChits.add(new ActionChit(Weight.HEAVY, ActionChit.Action.FIGHT, 4, 2));
		
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 5, 0));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 3, 5));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 4, 3));
		
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 4, 0));
		
		//move chits
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 3, 5));
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 4, 3));
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
