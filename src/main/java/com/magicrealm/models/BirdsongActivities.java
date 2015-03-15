package com.magicrealm.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.magicrealm.activity.Activity;
import com.magicrealm.utils.TileClearingLocation;

public class BirdsongActivities implements Serializable {
	
	private static final long serialVersionUID = -7701152441111645549L;
	private TileClearingLocation clearing;
	private ArrayList<Activity> queuedActivities;
	
	public BirdsongActivities(TileClearingLocation clearing) {
		queuedActivities = new ArrayList<Activity>();
		this.clearing = clearing;
	}

	public TileClearingLocation getClearing() {
		return clearing;
	}

	public void setClearing(TileClearingLocation clearing) {
		this.clearing = clearing;
	}

	public ArrayList<Activity> getQueuedActivities() {
		return queuedActivities;
	}

	public void setQueuedActivities(ArrayList<Activity> activities) {
		this.queuedActivities = activities;
	}
	
}
