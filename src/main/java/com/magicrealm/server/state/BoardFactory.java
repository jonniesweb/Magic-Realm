package com.magicrealm.server.state;

import com.magicrealm.models.board.DefaultTilePlacementStrategy;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.board.TilePlacementStrategy;
import com.magicrealm.models.tiles.MapChitPlacementStrategy;
import com.magicrealm.models.tiles.NormalMapChitPlacementStrategy;
import com.magicrealm.models.treasure.BasicTreasaurePlacementStrategy;
import com.magicrealm.models.treasure.TreasurePlacementStrategy;

public class BoardFactory {
	private MapChitPlacementStrategy chitPlacementStrategy;
	private TreasurePlacementStrategy treasurePlacementStrategy;
	private TilePlacementStrategy tilePlacementStrategy;

	public BoardFactory() {
	}
	
	public void setTilePlacementStrategy(TilePlacementStrategy strategy) {
		this.tilePlacementStrategy = strategy;
	}
	
	public void setMapChitPlacementStrategy(MapChitPlacementStrategy placementStrategy) {
		this.chitPlacementStrategy = placementStrategy;
	}
	
	public void setTreasurePlacementStrategy(TreasurePlacementStrategy placementStrategy) {
		this.treasurePlacementStrategy = placementStrategy;
	}
	
	public MagicRealmHexEngineModel create() {
		// do tile placement
		if (tilePlacementStrategy == null)
			tilePlacementStrategy = new DefaultTilePlacementStrategy();
		MagicRealmHexEngineModel model = tilePlacementStrategy.create();
		
		// do map chit placement
		if (chitPlacementStrategy == null)
			chitPlacementStrategy = new NormalMapChitPlacementStrategy();
		chitPlacementStrategy.placeMapChits(model);
		
		// do treasure placement
		if (treasurePlacementStrategy == null)
			treasurePlacementStrategy = new BasicTreasaurePlacementStrategy();
		treasurePlacementStrategy.placeTreasure(model);
		
		return model;
	}
}
