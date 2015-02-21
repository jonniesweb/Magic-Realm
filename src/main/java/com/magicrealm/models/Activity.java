package com.magicrealm.models;

import com.magicrealm.gui.SelectActivityPane;

public abstract class Activity implements Executable {
	
	public enum ActivityType {MOVE, HIDE, SEARCH};
	public ActivityType activity;
	
	public static Activity buildActivity(ActivityType type, SelectActivityPane activity) {
		//TODO build activities
		return null;
	}
	
	public String toString() {
		return activity.name().charAt(0)+"";
	}

}
