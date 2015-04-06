package com.magicrealm.server.state;

import com.magicrealm.models.monsters.Ghost;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class MidnightState extends ServerState {

	public MidnightState(ServerGameState instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		for (IClientService clientService : getGameState().getClientServices()) {
			clientService.midnightStarted();
		}
		
		regenerateMonsters();
		changePhase();
	}
	
	public void regenerateMonsters() {
		if(getDay() % 7 == 0) {
			getGameState().getBoard().resetMonsters();
			
			// hard code for now
			getGameState().getBoard().placeChit(TileType.EV, 5, new Ghost());
			getGameState().getBoard().placeChit(TileType.EV, 5, new Ghost());
		}
	}
	
	public void changePhase() {
		// switch state to birdsong
		BirdsongState birdsongState = new BirdsongState(getGameState());
		getGameState().setState(birdsongState);
		birdsongState.init();
	}

}
