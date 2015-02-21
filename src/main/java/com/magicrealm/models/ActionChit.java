package com.magicrealm.models;

public class ActionChit extends Belonging {
	
	public static enum Action {MOVE, FIGHT, DUCK, BERSERK, MAGIC};
	private Action action;
	private int totalCharges; //extra effort in game rules
	private int charges;
	private int time;
	private Weight strength;
	private boolean wounded;
	
	public ActionChit(Weight s, Action a, int t, int e) {
		action = a;
		totalCharges = e;
		charges = totalCharges;
		time = t;
		strength = s;
		wounded = false;
	}
	
	public void fatigue() {
		if(charges == 0)
			wounded = true;
		else
			-- charges;
	}
	
	public void restore() {
		if(wounded)
			wounded = false;
		else
			++ charges;
	}
	
	public Action getAction() {
		return action;
	}

	public int getTotalCharges() {
		return totalCharges;
	}

	public int getCharges() {
		return charges;
	}

	public int getTime() {
		return time;
	}

	public Weight getStrength() {
		return strength;
	}

	public boolean isWounded() {
		return wounded;
	} 

}
