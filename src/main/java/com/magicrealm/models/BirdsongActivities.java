package com.magicrealm.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.magicrealm.activity.Activity;
import com.magicrealm.activity.Move;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class BirdsongActivities implements Serializable {
	
	private static final long serialVersionUID = -7701152441111645549L;
	private static final int SUNLIGHT_PHASES = 2;
	private static final int BASIC_PHASES = 2;
	private TileClearingLocation clearing;
	private ArrayList<Activity> queuedActivities;
	private boolean inCave;
	private int mountainPhases = 0;
	
	public BirdsongActivities(TileClearingLocation clearing, boolean inCave) {
		queuedActivities = new ArrayList<Activity>();
		this.clearing = clearing;
		this.inCave = inCave;
	}
	
	public boolean addActivity(Activity activity) {

		if(activity instanceof Move) {
			Move moveActivity = (Move) activity;
			TileClearingLocation tcl = moveActivity.getLocation();
			TileClearing tc = ClientGameState.getInstance().getModel().getClearing(tcl);
			
			// validate activity against move restrictions
			switch (tc.getClearingType()) {
			case CAVE:
				if(getCurrentPhases() >= BASIC_PHASES)
					return false;
				inCave = true;
				break;
			case MOUNTAIN:
				if(getCurrentPhases() + 1 >= getMaxPhases())
					return false;
				++mountainPhases;
				break;
			default:
				if(getCurrentPhases() >= getMaxPhases())
					return false;
				break;
			}
			
			clearing = tcl;
		} else {
			if(getCurrentPhases() >= getMaxPhases())
				return false;
		}
		
		queuedActivities.add(activity);
		return true;
	}
	
	public int getCurrentPhases() {
		return queuedActivities.size() + mountainPhases;
	}
	
	public int getMaxPhases() {
		int phases = 0;
		if(!inCave)
			phases += SUNLIGHT_PHASES;
		return phases + BASIC_PHASES;
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
