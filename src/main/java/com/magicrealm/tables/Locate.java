package com.magicrealm.tables;

import com.magicrealm.GameState;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.GameLog;

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
			GameState.getInstance().getCharacter().addDiscovery(chit);
			GameLog.log("You discovered site " + chit.getType());
		} else {
			GameLog.log("You found nothing of value");
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
