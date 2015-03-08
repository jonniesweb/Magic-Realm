package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class Move extends Activity {
	
	private TileClearingLocation location;
	
	public Move(TileClearingLocation location) {
		this.activity = ActivityType.MOVE;
		this.location = location;
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		GameState.getInstance().getModel().moveChitTo(location.getTileType(), location.getClearingNumber(), player);
	}
	
	public TileClearingLocation getLocation() {
		return location;
	}

	public void setLocation(TileClearingLocation location) {
		this.location = location;
	}

	public String toString() {
		String s = super.toString();
		s += location.getTileType();
		s += location.getClearingNumber();
		return s;
	}

}
