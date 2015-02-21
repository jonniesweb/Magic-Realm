package com.magicrealm;

import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

public class GameState {

	private static GameState instance = new GameState();
	
	private DefaultMagicRealmHexEngineModel model;
	private Character character;
	
	public GameState() {
		// stuff
	}
	
	public static GameState getInstance() {
		return instance;
	}
	
	public MagicRealmHexEngineModel getModel() {
		return model;
	}

	public void setModel(DefaultMagicRealmHexEngineModel model) {
		this.model = model;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
}
