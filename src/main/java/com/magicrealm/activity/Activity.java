package com.magicrealm.activity;

import java.io.Serializable;

import com.magicrealm.gui.SelectActivityPane;

public abstract class Activity implements Serializable, Executable {
	
	public enum ActivityType {MOVE, HIDE, REST, SEARCH, ALERT};
	public ActivityType activity;
	
	public static Activity buildActivity(ActivityType type, SelectActivityPane activity) {
		switch (type) {
		case MOVE:
			return new Move(activity.getMoveLocation());
		case HIDE:
			return new Hide();
		case REST:
			return new Rest();
		case SEARCH:
			return new Search();
		case ALERT:
			return new Alert();
		}
		return null;
	}
	
	public String toString() {
		return activity.name().charAt(0) + "";
	}

}
