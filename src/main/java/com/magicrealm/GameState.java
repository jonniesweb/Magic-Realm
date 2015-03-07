package com.magicrealm;

import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

@Deprecated
public class GameState {

	private static GameState instance = new GameState();
	
	private MagicRealmHexEngineModel model;
	private MRCharacter character;
	
	public GameState() {
	}
	
	public static GameState getInstance() {
		return instance;
	}
	
	public MagicRealmHexEngineModel getModel() {
		return model;
	}

	public void setModel(MagicRealmHexEngineModel model) {
		this.model = model;
	}
	
	public MRCharacter getCharacter() {
		return character;
	}
	
	public void setCharacter(MRCharacter character) {
		this.character = character;
	}
	
}
