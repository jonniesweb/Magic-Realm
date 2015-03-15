package com.magicrealm.tables;

import com.magicrealm.server.ServerGameState;

public class Peer extends Table {
	
	public Peer(ServerGameState gameState, String clientId) {
		super(gameState, clientId);
	}

	@Override
	public void one() {
		nothing();
	}

	@Override
	public void two() {
		five();
		secretPath();
	}

	@Override
	public void three() {
		four();
		secretPath();
	}

	@Override
	public void four() {
		getGameState().getClientService(getClientId()).sendMessage("No hidden enemies here");
	}

	@Override
	public void five() {
		clues();
	}

	@Override
	public void six() {
		nothing();
	}

}
