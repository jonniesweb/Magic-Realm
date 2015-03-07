package com.magicrealm.models;

import java.util.ArrayList;

import com.magicrealm.utils.TileClearingLocation;

public class BirdsongActivities {
	
	private TileClearingLocation clearing;
	private ArrayList<Activity> queuedActivities;
	
	public BirdsongActivities() {
		queuedActivities = new ArrayList<Activity>();
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
