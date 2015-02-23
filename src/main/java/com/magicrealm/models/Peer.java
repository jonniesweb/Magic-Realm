package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.tiles.TileClearing;

public class Peer extends Table {
	
	@Override
	public void two() {
		// TODO Auto-generated method stub
		five();
		secretPath();
	}

	@Override
	public void three() {
		// TODO Auto-generated method stub
		four();
		secretPath();
	}

	@Override
	public void four() {
		// TODO Auto-generated method stub
		print("No hidden enemies here");
	}

	@Override
	public void five() {
		// TODO Auto-generated method stub
		clues();
	}

	@Override
	public void six() {
		// TODO Auto-generated method stub
		nothing();
	}

}
