package com.magicrealm.server.state;

import com.magicrealm.server.ServerGameState;

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

}
