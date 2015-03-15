package com.magicrealm.activity;

import com.magicrealm.server.ServerGameState;

public interface Executable {
	
	public void execute(ServerGameState gameState, String clientId);

}
