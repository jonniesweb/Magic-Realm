package com.magicrealm.server.state;

import java.util.Map;

import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.networking.ClientService;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class DaylightState extends ServerState {

	private Map<String, BirdsongActivities> activities;

	public DaylightState(ServerGameState instance, Map<String, BirdsongActivities> activities) {
		super(instance);
		this.activities = activities;
		for (IClientService iterable_element : ServerGameState.getInstance().getClientServices()) {
			iterable_element.clientSelect(new String[]{"dog", "cat", "raccooon"}, "choose rodent");
		}
	}
	
	/**
	 * Run the activities for the first character, in the order specified. Then
	 * repeat for the other characters.
	 */
	public void runActivities() {
		
	}
	
}
