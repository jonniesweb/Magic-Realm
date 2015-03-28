package com.magicrealm.client;

import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.gui.StartGameFrame;
import com.magicrealm.gui.UIMediator;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.INet;

public class ClientGameState {
	
private static ClientGameState instance;
	
	private MagicRealmHexEngineModel model;
	private MRCharacter character;
	private UIMediator uiMediator;
	private INet service;
	private BirdsongActivities activities;
	private JFrame startGameFrame;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
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
		MagicRealmHexEngineModel old = this.model;
		this.model = model;
		// update the view on change
		pcs.firePropertyChange("model", old, model);
	}
	
	public MRCharacter getCharacter() {
		return character;
	}
	
	public void setCharacter(MRCharacter character) {
		MRCharacter old = this.character;
		this.character = character;
		pcs.firePropertyChange("character", old, character);
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


	public void setStartGameFrame(StartGameFrame startGameFrame) {
		this.startGameFrame = startGameFrame;
	}
	
	public JFrame getStartGameFrame() {
		return startGameFrame;
	}


	public PropertyChangeSupport getPcs() {
		return pcs;
	}


	public UIMediator getUiMediator() {
		return uiMediator;
	}


	public void setUiMediator(UIMediator uiMediator) {
		this.uiMediator = uiMediator;
	}
}
