package com.magicrealm.server.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class BirdsongState extends ServerState {
	
	private Map<String, BirdsongActivities> activities = new HashMap<>();

	public BirdsongState(ServerGameState instance) {
		super(instance);
		
		// place the characters
		Set<MRCharacter> characters = instance.getCharacters();
		MagicRealmHexEngineModel board = instance.getBoard();
		Map<dwelling, TileClearingLocation> dwellingLocations = board.getDwellingLocations();
		for (MRCharacter c : characters) {
			board.placeChit(dwellingLocations.get(c.getStartingLocation()), c);
		}
		
		// notify clients of birdsong
		for (IClientService service : getGameState().getClientServices()) {
			service.birdsongStarted();
			service.sendMessage("Birdsong has begun");
		}
	}
	
	/**
	 * Clients queue activities in this state, then in the next state they get
	 * executed.
	 * @param clientId
	 * @param activities
	 */
	public void addActivities(String clientId, BirdsongActivities activities) {
		// TODO: verify that the activities are valid before adding it 
		this.activities.put(clientId, activities);
		
		/*
		 * Check if everyone has submitted their activities. If so, change the
		 * state to a new one.
		 */
		if (this.activities.size() == getGameState().getNumberOfPlayers()) {
			getGameState().setState(new DaylightState(getGameState(), this.activities));
		}
	}
	
}
