package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.server.ServerGameState;
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
		ServerGameState.getInstance().getBoard().moveChitTo(location.getTileType(), location.getClearingNumber(), player);
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
