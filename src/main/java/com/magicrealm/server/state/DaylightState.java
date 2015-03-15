package com.magicrealm.server.state;

import java.util.List;
import java.util.Map;

import com.magicrealm.activity.Activity;
import com.magicrealm.characters.MRCharacter;
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
		
		runActivities();
	}
	
	/**
	 * Run the activities for the first character, in the order specified. Then
	 * repeat for the other characters.
	 */
	public void runActivities() {
		List<String> playerOrder = getGameState().getPlayerOrder();
		
		for (String clientId : playerOrder) {
			BirdsongActivities playerActivities = activities.get(clientId);
			for (Activity activity : playerActivities.getQueuedActivities()) {
				activity.execute(getGameState(), clientId);
			}
		}
		
	}
	
}
