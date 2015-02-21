package com.magicrealm.models;

import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;

public class Move extends Activity {
	
	private GameTile tile;
	private TileClearing clearing;
	
	public Move(GameTile tile, TileClearing clearing) {
		this.tile = tile;
		this.clearing = clearing;
	}
	
	@Override
	public void execute(Character player) {
		// TODO Auto-generated method stub
		player.move(tile, clearing);
	}

}
