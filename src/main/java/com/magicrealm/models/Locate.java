package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.utils.GameLog;

public class Locate extends Table {

	@Override
	public void two() {
		// TODO Auto-generated method stub
		clues();
		secretPath();
	}

	@Override
	public void three() {
		// TODO Auto-generated method stub
		secretPath();
	}

	@Override
	public void four() {
		// TODO Auto-generated method stub
		ClearingMapChit chit = getChitAtLocation();
		if(chit != null) {
			GameState.getInstance().getCharacter().addDiscovery(chit);
			GameLog.log("You discovered site " + chit.getType());
		} else {
			GameLog.log("You found nothing of value");
		}
	}

	@Override
	public void five() {
		// TODO Auto-generated method stub
		nothing();
	}

	@Override
	public void six() {
		// TODO Auto-generated method stub
		nothing();
	}

}
