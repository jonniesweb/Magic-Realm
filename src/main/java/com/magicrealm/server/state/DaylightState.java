package com.magicrealm.server.state;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.activity.Activity;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.server.ServerGameState;

public class DaylightState extends ServerState {

	private Map<String, BirdsongActivities> activities;
	private final Log log = LogFactory.getLog(DaylightState.class);

	public DaylightState(ServerGameState instance, Map<String, BirdsongActivities> activities) {
		super(instance);
		this.activities = activities;
	}
	
	/**
	 * Run the activities for the first character, in the order specified. Then
	 * repeat for the other characters.
	 */
	public void runActivities() {
		List<String> playerOrder = getGameState().getPlayerOrder();
		
		for (String clientId : playerOrder) {
			log.info("Running activities for clientId: " + clientId);
			BirdsongActivities playerActivities = activities.get(clientId);
			for (Activity activity : playerActivities.getQueuedActivities()) {
				log.info("Executing activity " + activity);
				activity.execute(getGameState(), clientId);
			}
		}
		
		BirdsongState birdsongState = new BirdsongState(getGameState());
		getGameState().setState(birdsongState);
		birdsongState.init();
		
	}
	
}
