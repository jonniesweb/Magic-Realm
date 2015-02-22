package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.models.weapons.ShortSword;
import com.magicrealm.models.weapons.ThrustingSword;
import com.magicrealm.utils.ImageCache;

public class Swordsman extends MRCharacter {
	
	public Swordsman() {
		name = "Swordsman";
		vulnerability = Weight.MEDIUM;
		setActiveWeapon(new ThrustingSword());
//		startingLocation =
		setupActionChits();
	}
	
	public void setupActionChits() {
		//fight chits
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 3, 2));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 4, 1));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 5, 0));
		
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 2, 5));
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 3, 1));
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 4, 0));
		
		//move chits
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 4, 1));
		
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 2, 2));
		moveChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 3, 3));
		moveChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 4, 0));
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return ImageCache.getImage("swordsman");
	}

	@Override
	public String getImageName() {
		// TODO Auto-generated method stub
		return "swordsman";
	}

}
