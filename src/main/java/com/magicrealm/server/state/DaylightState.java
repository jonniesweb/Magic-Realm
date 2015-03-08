package com.magicrealm.server.state;

import java.util.Map;

import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.server.ServerGameState;

public class DaylightState extends ServerState {

	private Map<String, BirdsongActivities> activities;

	public DaylightState(ServerGameState instance, Map<String, BirdsongActivities> activities) {
		super(instance);
		this.activities = activities;
	}
	
	/**
	 * Run the activities for the first character, in the order specified. Then
	 * repeat for the other characters.
	 */
	public void runActivities() {
		
	}
	
}
