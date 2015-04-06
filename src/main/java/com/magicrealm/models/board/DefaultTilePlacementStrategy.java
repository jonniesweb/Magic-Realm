package com.magicrealm.models.board;

import com.magicrealm.models.Dwelling;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.monsters.Ghost;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;

/**
 * Create a 7 x 5 board and place tiles, dwellings and two ghosts
 */
public class DefaultTilePlacementStrategy extends TilePlacementStrategy {
	
	public DefaultTilePlacementStrategy() {
		model = new MagicRealmHexEngineModel(7, 5);
		
		// place tiles
		model.setValueAt(1, 1, new GameTile(TileType.PW, 1));
		model.setValueAt(1, 3, new GameTile(TileType.LW, 2));
		model.setValueAt(2, 1, new GameTile(TileType.M, 0));
		model.setValueAt(2, 2, new GameTile(TileType.CS, 3));
		model.setValueAt(2, 3, new GameTile(TileType.R, 3));
		model.setValueAt(2, 4, new GameTile(TileType.AV, 1));
		model.setValueAt(3, 0, new GameTile(TileType.CN, 2));
		model.setValueAt(3, 1, new GameTile(TileType.BV, 0));
		model.setValueAt(3, 2, new GameTile(TileType.MW, 3));
		model.setValueAt(3, 3, new GameTile(TileType.NW, 4));
		model.setValueAt(4, 0, new GameTile(TileType.HP, 3));
		model.setValueAt(4, 1, new GameTile(TileType.B, 0));
		model.setValueAt(4, 2, new GameTile(TileType.OW, 5));
		model.setValueAt(4, 3, new GameTile(TileType.DW, 1));
		model.setValueAt(4, 4, new GameTile(TileType.CV, 1));
		model.setValueAt(5, 0, new GameTile(TileType.EV, 3));
		model.setValueAt(5, 1, new GameTile(TileType.L, 5));
		model.setValueAt(5, 2, new GameTile(TileType.CG, 4));
		model.setValueAt(5, 3, new GameTile(TileType.DV, 3));
		model.setValueAt(6, 1, new GameTile(TileType.CF, 0));
		
		// place dwellings
		model.placeChit(TileType.DV, 5, new Dwelling(dwelling.guard));
		model.placeChit(TileType.CV, 5, new Dwelling(dwelling.house));
		model.placeChit(TileType.AV, 5, new Dwelling(dwelling.chapel));
		model.placeChit(TileType.BV, 5, new Dwelling(dwelling.inn));
		
		// place monsters
		model.placeChit(TileType.EV, 5, new Ghost());
		model.placeChit(TileType.EV, 5, new Ghost());
	}
}