package com.magicrealm.models.tiles;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.GameTile.TileType;

/**
 * A strategy for placing specific map chits on specific tiles. Used in cheat
 * mode for building a board.
 */
public class CheatModeMapChitPlacementStrategy extends MapChitPlacementStrategy {
	
	private final Log log = LogFactory.getLog(CheatModeMapChitPlacementStrategy.class);
	
	private Map<TileType, WarningChit> warningChits;
	private Map<TileType, ClearingMapChit> clearingChits;

	public CheatModeMapChitPlacementStrategy(Map<TileType, ClearingMapChit> clearingChits,
			Map<TileType, WarningChit> warningChits) {
		super();
		this.clearingChits = clearingChits;
		this.warningChits = warningChits;
	}

	@Override
	public void placeMapChits(MagicRealmHexEngineModel model) {
		// place warning chits
		if (warningChits != null) {
			for (TileType tileType : warningChits.keySet()) {
				GameTile tile = model.getTile(tileType);
				WarningChit warningChit = warningChits.get(tileType);
				tile.setWarningChit(warningChit);
				log.debug("set warning chit in tile " + tile.getTileType() + " to " + warningChit);
			}
		}
		
		// place clearing chits
		if (clearingChits != null) {
			for (TileType tileType : clearingChits.keySet()) {
				GameTile tile = model.getTile(tileType);
				ClearingMapChit mapChit = clearingChits.get(tile);
				tile.setSiteSoundChit(mapChit);
				log.debug("set map chit in tile " + tile.getTileType() + " to " + mapChit);
			}
		}
	}
	
}
