package com.magicrealm.server.state;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.activity.Activity;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.chits.SoundChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

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
			
			TileClearingLocation loc0 = getGameState().getBoard().getChitLocation(character);
			GameTile tile0 = getGameState().getBoard().getTile(loc0.getTileType());
			
			BirdsongActivities playerActivities = activities.get(clientId);
			for (Activity activity : playerActivities.getQueuedActivities()) {
				log.info("Executing activity " + activity);
				activity.execute(getGameState(), clientId);
				updateClients();
			}
			
			TileClearingLocation loc = getGameState().getBoard().getChitLocation(character);
			GameTile tile = getGameState().getBoard().getTile(loc.getTileType());
			
			if(tile0 != tile) {
			IClientService service = getGameState().getClientService(clientId);
			
			WarningChit wc = tile.getWarningChit();
				if(wc != null)
					service.sendMessage("Warning! "+wc.getWarningTile().name());
				if(tile.getSiteSoundChit() instanceof SoundChit) {
					String sound = ((SoundChit) tile.getSiteSoundChit()).getSoundType().name();
					sound = sound.substring(0, sound.length() - 1);
					service.sendMessage("Scary sounds of "+sound);
				}
			}
		}
		
		// switch state to evening
		EveningState eveningState = new EveningState(getGameState());
		getGameState().setState(eveningState);
		eveningState.init();
	}
	
}
