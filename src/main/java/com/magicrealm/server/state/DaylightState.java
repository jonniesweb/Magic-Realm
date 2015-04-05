package com.magicrealm.server.state;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.activity.Activity;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.server.ServerGameState.User;

public class DaylightState extends ServerState {

	private Map<String, BirdsongActivities> activities;
	private final Log log = LogFactory.getLog(DaylightState.class);

	public DaylightState(ServerGameState instance, Map<String, BirdsongActivities> activities) {
		super(instance);
		this.activities = activities;
	}
	
	public void init() {
		for (IClientService clientService : getGameState().getClientServices()) {
			clientService.daylightStarted();
		}
		
		runActivities();
	}
	
	/**
	 * Run the activities for the first character, in the order specified. Then
	 * repeat for the other characters.
	 */
	public void runActivities() {
		List<String> playerOrder = getGameState().getPlayerOrder();
		
		// run activities
		for (String clientId : playerOrder) {
			log.info("Running activities for clientId: " + clientId);
			
			// unalert weapon and unhide character for next day
			MRCharacter character = getGameState().getCharacter(clientId);
			character.reveal();
			character.getActiveWeapon().sleep();
			
			BirdsongActivities playerActivities = activities.get(clientId);
			for (Activity activity : playerActivities.getQueuedActivities()) {
				log.info("Executing activity " + activity);
				activity.execute(getGameState(), clientId);
				updateClients();
			}
		}
		
		// switch state to evening
		EveningState eveningState = new EveningState(getGameState());
		getGameState().setState(eveningState);
		eveningState.init();
	}

	/**
	 * Update the board and characters for all connected players
	 */
	private void updateClients() {
		MagicRealmHexEngineModel board = getGameState().getBoard();
		
		for (User user : getGameState().getAllUsers()) {
			IClientService clientService = user.clientService;
			clientService.updateBoard(board);
			clientService.updateCharacter(user.character);
		}
	}
	
}
