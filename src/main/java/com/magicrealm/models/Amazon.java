package com.magicrealm.models;

public class Amazon extends Character {
	
	public Amazon() {
		name = "Amazon";
		vulnerability = Weight.MEDIUM;
		actionChits = new ActionChit[12];
//		startingLocation = 
	}
	
	public void setupActionChits() {
		actionChits[0] = new ActionChit(ActionChit.ACTION.MOVE, 2, 4, Weight.HEAVY);
	}

}
