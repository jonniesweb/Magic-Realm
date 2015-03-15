package com.magicrealm.server.state;

import java.util.Map;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class PlayerConnectState extends ServerState {

	public PlayerConnectState(ServerGameState instance) {
		super(instance);
	}

	/**
	 * Starts a new game, setting the state to Birdsong, creating the gameboard
	 * and notifying the clients
	 */
	public void startGame() {
		// init the board
		getGameState().setBoard(new DefaultMagicRealmHexEngineModel(0, 0));
		
		// place the characters
		Set<MRCharacter> characters = getGameState().getCharacters();
		MagicRealmHexEngineModel board = getGameState().getBoard();
		Map<dwelling, TileClearingLocation> dwellingLocations = board.getDwellingLocations();
		for (MRCharacter c : characters) {
			board.placeChit(dwellingLocations.get(c.getStartingLocation()), c);
		}
		
		// set state to birdsong
		BirdsongState state = new BirdsongState(getGameState());
		getGameState().setState(state);
		
		// notify clients that the game has started
		for (IClientService clientService : getGameState().getClientServices()) {
			clientService.gameStarted(getGameState().getBoard());
		}
		state.init();
	}
	
}
