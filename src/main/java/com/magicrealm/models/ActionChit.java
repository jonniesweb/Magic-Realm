package com.magicrealm.models;

public class ActionChit extends Belonging {
	
	public static enum Action {MOVE, FIGHT, DUCK, BERSERK, MAGIC};
	private Action action;
	private int totalCharges; //extra effort in game rules
	private int charges;
	private int time;
	private int cost;
	private Weight strength;
	private boolean wounded;
	
	public ActionChit(Weight s, Action a, int t, int e, int c) {
		action = a;
		totalCharges = e;
		cost = c;
		charges = totalCharges;
		time = t;
		strength = s;
		wounded = false;
	}
	
	public void fatigue() {
		if(charges == 0 || cost == 0)
			wounded = true;
		else
			charges = Math.max(charges - cost, 0);
	}
	
	public void restoreCharge() {
		if(wounded)
			wounded = false;
		else
			charges += cost;
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
	
	public String toString() {
		String s = this.action.name().charAt(0)+"";
		s += this.time+"";
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		ActionChit ac;
		if(obj instanceof ActionChit)
			ac = (ActionChit) obj;
		else
			return false;
		return this.action == ac.action && this.strength == ac.strength && this.time == ac.time;
	}

}
