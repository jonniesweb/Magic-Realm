package com.magicrealm.server.main;

import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.server.ServerGameState;

public class ServerMain {
	
	public static void main(String[] args) {
		
		ServerGameState.getInstance().setBoard(new DefaultMagicRealmHexEngineModel(0, 0));
		
		
	}
	
}
