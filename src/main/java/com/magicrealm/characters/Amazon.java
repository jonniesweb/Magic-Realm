package com.magicrealm.characters;

import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.Weight;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Breastplate;
import com.magicrealm.models.armors.Helmet;
import com.magicrealm.models.armors.Shield;
import com.magicrealm.models.weapons.ShortSword;

public class Amazon extends MRCharacter {
	
	public Amazon() {
		super(character.amazon);
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		weapons.add(new ShortSword());
		weapons.get(0).activate();
		Armor helmet = new Helmet();
		helmet.activate();
		Armor breastplate = new Breastplate();
		breastplate.activate();
		Armor shield = new Shield();
		shield.activate();
		armors.add(helmet);
		armors.add(breastplate);
		armors.add(shield);
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
	public String getImageName() {
		return "amazon";
	}

	@Override
	public dwelling[] getPossibleStartingLocations() {
		dwelling[] starting = { dwelling.inn };
		return starting;
	}
}
