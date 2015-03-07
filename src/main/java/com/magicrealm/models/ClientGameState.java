package com.magicrealm.models;

import com.magicrealm.gui.BoardView;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.INet;

public class ClientGameState {
	
private static ClientGameState instance;
	
	private MagicRealmHexEngineModel model;
	private MRCharacter character;
	BoardView view;
	INet service;
	BirdsongActivities activities;
	
	public ClientGameState() {
	}
	
	
	public static ClientGameState getInstance() {
		if(instance == null)
			instance = new ClientGameState();
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

	public BoardView getView() {
		return view;
	}

	public void setView(BoardView view) {
		this.view = view;
	}

	public INet getService() {
		return service;
	}

	public void setService(INet service) {
		this.service = service;
	}


	public BirdsongActivities getActivities() {
		return activities;
	}


	public void setActivities(BirdsongActivities activities) {
		this.activities = activities;
	}
	

}
