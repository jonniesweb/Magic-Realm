package com.magicrealm.characters;

import java.awt.Image;

import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.ShortSword;
import com.magicrealm.utils.ImageCache;

public class Amazon extends MRCharacter {
	
	public Amazon() {
		super(character.amazon);
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		weapons.add(new ShortSword());
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

	@Override
	public dwelling[] getPossibleStartingLocations() {
		dwelling[] starting = { dwelling.inn };
		return starting;
	}
}
