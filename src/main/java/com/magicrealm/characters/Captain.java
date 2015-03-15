package com.magicrealm.characters;

import java.awt.Image;

import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.ShortSword;
import com.magicrealm.utils.ImageCache;

public class Captain extends MRCharacter {
	
	public Captain() {
		super(character.captain);
		name = "Captain";
		vulnerability = Weight.MEDIUM;
		weapons.add(new ShortSword());
		weapons.get(0).activate();
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

	public dwelling[] getPossibleStartingLocations() {
		dwelling[] starting = { dwelling.inn, dwelling.house, dwelling.guard };
		return starting;
	}

}
