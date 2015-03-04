package com.magicrealm.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

/**
 * Singleton class for all game state.
 */
public class ServerGameState {
	private MagicRealmHexEngineModel board;
	private Map<String, MRCharacter> characters = new HashMap<>();
	private static ServerGameState instance;
	
	protected ServerGameState() {
	}
	
	public void setBoard(MagicRealmHexEngineModel board) {
		this.board = board;
	}
	
	public MagicRealmHexEngineModel getBoard() {
		return board;
	}
	
	public Collection<MRCharacter> getCharacters() {
		return characters.values();
	}
	
	public MRCharacter getCharacter(String clientId) {
		return characters.get(clientId);
	}
	
	public void addCharacter(String clientId, MRCharacter character) {
		characters.put(clientId, character);
	}
	
	public static ServerGameState getInstance() {
		if (instance == null) {
			instance = new ServerGameState();
		}
		return instance;
	}
}
