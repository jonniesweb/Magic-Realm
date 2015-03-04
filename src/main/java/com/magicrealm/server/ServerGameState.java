package com.magicrealm.server;

import java.util.HashSet;
import java.util.Set;

import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

/**
 * Singleton class for all game state.
 */
public class ServerGameState {
	private MagicRealmHexEngineModel board;
	private static Set<MRCharacter> characters = new HashSet<>();
	
	/**
	 * @param board
	 */
	public ServerGameState(MagicRealmHexEngineModel board) {
		this.board = board;
	}
	
	public MagicRealmHexEngineModel getBoard() {
		return board;
	}
	
	public static Set<MRCharacter> getCharacters() {
		return characters;
	}
	
}
