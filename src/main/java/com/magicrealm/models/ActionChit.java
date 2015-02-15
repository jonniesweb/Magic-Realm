package com.magicrealm.models;

public class ActionChit extends Belonging {
	
	public static enum ACTION {MOVE, FIGHT, DUCK, BERSERK, MAGIC};
	private ACTION action;
	private int extraEffort;
	private int time;
	private Weight strength;
//	private int magicRitual;
	
	public ActionChit(Weight s, ACTION a, int t, int e) {
		action = a;
		extraEffort = e;
		time = t;
		strength = s;
	}

}
