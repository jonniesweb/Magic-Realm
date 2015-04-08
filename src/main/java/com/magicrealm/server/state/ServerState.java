package com.magicrealm.server.state;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.server.ServerGameState.User;

public class ServerState {
	
	private static int day = 0;

	private ServerGameState gameState;

	public ServerState(ServerGameState instance) {
		this.gameState = instance;
	}

	public ServerGameState getGameState() {
		return gameState;
	}
	
	public int getDay() {
		return day;
	}
	
	public void incrementDay() {
		++day;
	}

	/**
	 * Update the board and characters for all connected players
	 */
	protected void updateClients() {
		MagicRealmHexEngineModel board = getGameState().getBoard();
		
		for (User user : getGameState().getAllUsers()) {
			IClientService clientService = user.clientService;
			clientService.updateBoard(board);
			clientService.updateCharacter(user.character);
		}
	}

}
