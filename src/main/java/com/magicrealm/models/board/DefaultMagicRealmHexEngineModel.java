package com.magicrealm.models.board;

import com.magicrealm.models.Dwelling;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.monsters.Ghost;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.NormalMapChitPlacementStrategy;
import com.magicrealm.models.treasure.BasicTreasaurePlacementStrategy;

public class DefaultMagicRealmHexEngineModel extends MagicRealmHexEngineModel {

	public DefaultMagicRealmHexEngineModel(int columns, int rows) {
		super(7, 5);
		
		// place tiles
		setValueAt(1, 1, new GameTile(TileType.PW, 1));
		setValueAt(1, 3, new GameTile(TileType.LW, 2));
		setValueAt(2, 1, new GameTile(TileType.M, 0));
		setValueAt(2, 2, new GameTile(TileType.CS, 3));
		setValueAt(2, 3, new GameTile(TileType.R, 3));
		setValueAt(2, 4, new GameTile(TileType.AV, 1));
		setValueAt(3, 0, new GameTile(TileType.CN, 2));
		setValueAt(3, 1, new GameTile(TileType.BV, 0));
		setValueAt(3, 2, new GameTile(TileType.MW, 3));
		setValueAt(3, 3, new GameTile(TileType.NW, 4));
		setValueAt(4, 0, new GameTile(TileType.HP, 3));
		setValueAt(4, 1, new GameTile(TileType.B, 0));
		setValueAt(4, 2, new GameTile(TileType.OW, 5));
		setValueAt(4, 3, new GameTile(TileType.DW, 1));
		setValueAt(4, 4, new GameTile(TileType.CV, 1));
		setValueAt(5, 0, new GameTile(TileType.EV, 3));
		setValueAt(5, 1, new GameTile(TileType.L, 5));
		setValueAt(5, 2, new GameTile(TileType.CG, 4));
		setValueAt(5, 3, new GameTile(TileType.DV, 3));
		setValueAt(6, 1, new GameTile(TileType.CF, 0));
		
		// place dwellings
		placeChit(TileType.DV, 5, new Dwelling(dwelling.guard));
		placeChit(TileType.CV, 5, new Dwelling(dwelling.house));
		placeChit(TileType.AV, 5, new Dwelling(dwelling.chapel));
		placeChit(TileType.BV, 5, new Dwelling(dwelling.inn));
		
		// place monsters
		placeChit(TileType.EV, 5, new Ghost());
		placeChit(TileType.EV, 5, new Ghost());
		
		connectAllTheThings();
		
		new NormalMapChitPlacementStrategy(this).placeMapChits();
		
		new BasicTreasaurePlacementStrategy(this).placeTreasure();
	}
	
}
