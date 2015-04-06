package com.magicrealm.models.treasure;

import com.magicrealm.models.board.MagicRealmHexEngineModel;

/**
 * A strategy for how treasure should be placed on a board
 */
public abstract class TreasurePlacementStrategy {
	
	/**
	 * Place treasure on the given model according to the strategy
	 * 
	 * @param model
	 */
	public abstract void placeTreasure(MagicRealmHexEngineModel model);
	
	/**
	 * @param model
	 */
	public TreasurePlacementStrategy() {
	}
}
