package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.utils.ImageCache;

public class Amazon extends Character {
	
	public Amazon() {
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		actionChits = new ActionChit[12];
		activeWeapon = new ShortSword();
		activeWeapon.activate();
//		startingLocation = 
	}
	
	public void setupActionChits() {
		
		//fight chits
		actionChits[0] = new ActionChit(Weight.HEAVY, ActionChit.ACTION.FIGHT, 4, 2);
		actionChits[1] = new ActionChit(Weight.LIGHT, ActionChit.ACTION.FIGHT, 4, 0);
		actionChits[2] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.FIGHT, 5, 0);
		
		actionChits[3] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.FIGHT, 3, 2);
		actionChits[4] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.FIGHT, 3, 2);
		
		actionChits[5] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.FIGHT, 4, 1);
		actionChits[6] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.FIGHT, 4, 1);		
		
		//move chits
		actionChits[7] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.MOVE, 3, 1);
		actionChits[8] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.MOVE, 3, 1);
		actionChits[9] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.MOVE, 3, 1);
		
		actionChits[10] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.MOVE, 4, 0);
		actionChits[11] = new ActionChit(Weight.MEDIUM, ActionChit.ACTION.MOVE, 4, 0);
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
