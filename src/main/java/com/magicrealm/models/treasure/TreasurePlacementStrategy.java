package com.magicrealm.models.treasure;

import com.magicrealm.models.board.MagicRealmHexEngineModel;

public abstract class TreasurePlacementStrategy {
	
	protected MagicRealmHexEngineModel model;
	
	
	public abstract void placeTreasure();


	/**
	 * @param model
	 */
	public TreasurePlacementStrategy(MagicRealmHexEngineModel model) {
		this.setModel(model);
	}


	public MagicRealmHexEngineModel getModel() {
		return model;
	}


	public void setModel(MagicRealmHexEngineModel model) {
		this.model = model;
	}
}
