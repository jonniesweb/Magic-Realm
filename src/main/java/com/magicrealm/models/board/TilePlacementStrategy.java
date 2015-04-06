package com.magicrealm.models.board;

/**
 * A strategy for placing the tiles of a gameboard.
 */
public abstract class TilePlacementStrategy {
	
	protected MagicRealmHexEngineModel model;
	
	public TilePlacementStrategy() {
		super();
	}
	
	/**
	 * Create a gameboard using this class' strategy for placing tiles
	 * @return
	 */
	public MagicRealmHexEngineModel create() {
		model.connectAllTheThings();
		return model;
	}
	
}