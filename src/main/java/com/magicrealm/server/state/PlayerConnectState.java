package com.magicrealm.server.state;

import java.util.Map;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.CheatModeMapChitPlacementStrategy;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class PlayerConnectState extends ServerState {

	private Map<TileType, ClearingMapChit> clearingChits;
	private Map<TileType, WarningChit> warningChits;

	public PlayerConnectState(ServerGameState instance) {
		super(instance);
	}

	/**
	 * Starts a new game, setting the state to Birdsong, creating the gameboard
	 * and notifying the clients
	 */
	public void startGame() {
		// init the board
		getGameState().setBoard(getGameBoard());
		
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

	private MagicRealmHexEngineModel getGameBoard() {
		BoardFactory factory = new BoardFactory();
		
		// if clearing map chits were specified, give it to the factory
		if (clearingChits != null) {
			CheatModeMapChitPlacementStrategy chitPlacementStrategy = new CheatModeMapChitPlacementStrategy(
					clearingChits, warningChits);
			factory.setMapChitPlacementStrategy(chitPlacementStrategy);
		}
			
		return factory.create();
	}

	/**
	 * Specify the map chits to be placed on the board. Used in cheat mode.
	 * @param clearingChits
	 * @param warningChits
	 */
	public void setupChits(Map<TileType, ClearingMapChit> clearingChits,
			Map<TileType, WarningChit> warningChits) {
				this.clearingChits = clearingChits;
				this.warningChits = warningChits;
		
	}
	
}
