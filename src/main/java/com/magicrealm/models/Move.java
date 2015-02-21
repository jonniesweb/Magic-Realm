package com.magicrealm.models;

import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;

public class Move extends Activity {
	
	private GameTile tile;
	private TileClearing clearing;
	
	public Move(GameTile tile, TileClearing clearing) {
		this.activity = ActivityType.MOVE;
		this.tile = tile;
		this.clearing = clearing;
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		player.move(tile, clearing);
	}

	public GameTile getTile() {
		return tile;
	}

	public TileClearing getClearing() {
		return clearing;
	}
	
	public String toString() {
		String s = super.toString();
		s += tile.getTileType();
		s += clearing.getClearingNumber();
		return s;
	}

}
