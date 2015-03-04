package com.magicrealm.server.state;

import com.magicrealm.server.ServerGameState;

public class ServerState {

	private ServerGameState gameState;

	public ServerState(ServerGameState instance) {
		this.gameState = instance;
	}

	public ServerGameState getGameState() {
		return gameState;
	}

}
