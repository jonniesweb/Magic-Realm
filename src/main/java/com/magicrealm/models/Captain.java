package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.models.weapons.ShortSword;
import com.magicrealm.utils.ImageCache;

public class Captain extends MRCharacter {
	
	public Captain() {
		name = "Captain";
		vulnerability = Weight.MEDIUM;
		setActiveWeapon(new ShortSword());
//		startingLocation =
		setupActionChits();
	}
	
	public void setupActionChits() {
		//fight chits
		fightChits.add(new ActionChit(Weight.HEAVY, ActionChit.Action.FIGHT, 5, 3));
		fightChits.add(new ActionChit(Weight.HEAVY, ActionChit.Action.FIGHT, 6, 0));
		
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 3, 2));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 4, 3));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 5, 0));
		
		//move chits
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 3, 2));
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 4, 5));
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 5, 0));
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return ImageCache.getImage("captain");
	}

	@Override
	public String getImageName() {
		// TODO Auto-generated method stub
		return "captain";
	}

}