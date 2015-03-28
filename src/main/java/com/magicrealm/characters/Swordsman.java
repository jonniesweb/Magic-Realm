package com.magicrealm.characters;

import com.magicrealm.models.ActionChit;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.Weight;
import com.magicrealm.models.weapons.ThrustingSword;

public class Swordsman extends MRCharacter {
	
	public Swordsman() {
		super(character.swordsman);
		name = "Swordsman";
		vulnerability = Weight.MEDIUM;
		weapons.add(new ThrustingSword());
		weapons.get(0).activate();
		setupActionChits();
	}
	
	public void setupActionChits() {
		//fight chits
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 3, 2, 2));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 4, 1, 1));
		fightChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.FIGHT, 5, 0, 0));
		
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 2, 5, 2));
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 3, 1, 1));
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.FIGHT, 4, 0, 0));
		
		//move chits
		moveChits.add(new ActionChit(Weight.MEDIUM, ActionChit.Action.MOVE, 4, 1, 1));
		
		fightChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 2, 2, 2));
		moveChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 3, 3, 1));
		moveChits.add(new ActionChit(Weight.LIGHT, ActionChit.Action.MOVE, 4, 0, 0));
	}

	@Override
	public String getImageName() {
		return "swordsman";
	}

	public dwelling[] getPossibleStartingLocations() {
		dwelling[] starting = { dwelling.inn };
		return starting;
	}

}
