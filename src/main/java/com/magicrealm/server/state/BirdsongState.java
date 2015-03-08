package com.magicrealm.server.state;

import java.util.Map;
import java.util.Set;

import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class BirdsongState extends ServerState {

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
	
}
