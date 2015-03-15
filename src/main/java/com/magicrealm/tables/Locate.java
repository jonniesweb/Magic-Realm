package com.magicrealm.tables;

import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.server.ServerGameState;

public class Locate extends Table {

	public Locate(ServerGameState gameState, String clientId) {
		super(gameState, clientId);
	}

	@Override
	public void one() {
		nothing();
		
	}

	@Override
	public void two() {
		clues();
		secretPath();
	}

	@Override
	public void three() {
		secretPath();
	}

	@Override
	public void four() {
		ClearingMapChit chit = getChitAtLocation();
		if(chit != null) {
			getGameState().getCharacter(getClientId()).addDiscovery(chit);
			getGameState().getClientService(getClientId()).sendMessage("You discovered site " + chit.getType());
		} else {
			getGameState().getClientService(getClientId()).sendMessage("You found nothing of value");
		}
	}

	@Override
	public void five() {
		nothing();
	}

	@Override
	public void six() {
		nothing();
	}

}
