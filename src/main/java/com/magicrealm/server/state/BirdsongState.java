package com.magicrealm.server.state;

import java.util.Collection;
import java.util.Map;

import com.magicrealm.models.Dwelling;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class BirdsongState extends ServerState {

	public BirdsongState(ServerGameState instance) {
		super(instance);
		
		// place the characters
		Collection<MRCharacter> characters = instance.getCharacters();
		MagicRealmHexEngineModel board = instance.getBoard();
		Map<Dwelling, TileClearingLocation> dwellingLocations = board.getDwellingLocations();
		for (MRCharacter c : characters) {
			board.placeChit(dwellingLocations.get(c.getStartingLocation()), c);
		}
	}
	
}
