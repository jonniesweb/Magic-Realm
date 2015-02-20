package com.magicrealm.models;

public class Activity {
	
	public enum ActivityType {MOVE, HIDE, SEARCH};
	public ActivityType activity;
	
	public String toString() {
		return activity.name().charAt(0)+"";
	}

}
