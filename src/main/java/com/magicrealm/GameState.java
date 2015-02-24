package com.magicrealm;

import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.MRCharacter;

public class GameState {

	private static GameState instance = new GameState();
	
	private DefaultMagicRealmHexEngineModel model;
	private MRCharacter character;
	private boolean cheatMode;
	
	public GameState() {
		cheatMode = false;
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
	
	public MRCharacter getCharacter() {
		return character;
	}
	
	public void setCharacter(MRCharacter character) {
		this.character = character;
	}
	
	public boolean getCheatMode() {
		return cheatMode;
	}
	
	public void setCheatMode(boolean cheatMode) {
		this.cheatMode = cheatMode;
	}
	
}
