package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class Move extends Activity {
	
	private GameTile tile;
	private TileClearing clearing;
	private TileClearingLocation location;
	
	public Move(GameTile tile, TileClearing clearing) {
		this.activity = ActivityType.MOVE;
		this.tile = tile;
		this.clearing = clearing;
		this.location = new TileClearingLocation(tile.getTileType(), clearing.getClearingNumber());
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		GameState.getInstance().getModel().moveChitTo(tile.getTileType(), clearing.getClearingNumber(), player);
	}

	public GameTile getTile() {
		return tile;
	}

	public TileClearing getClearing() {
		return clearing;
	}
	
	public TileClearingLocation getLocation() {
		return location;
	}

	public void setLocation(TileClearingLocation location) {
		this.location = location;
	}

	public String toString() {
		String s = super.toString();
		s += tile.getTileType();
		s += clearing.getClearingNumber();
		return s;
	}

}
