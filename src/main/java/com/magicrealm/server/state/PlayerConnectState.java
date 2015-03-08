package com.magicrealm.server.state;

import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class PlayerConnectState extends ServerState {

	public PlayerConnectState(ServerGameState instance) {
		super(instance);
	}

	/**
	 * Starts a new game, setting the state to Birdsong
	 */
	public void startGame() {
		// init the board
		getGameState().setBoard(new DefaultMagicRealmHexEngineModel(0, 0));
		
		// set state to birdsong
		getGameState().setState(new BirdsongState(getGameState()));
		
	}
	
}
