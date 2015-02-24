package com.magicrealm.models;

import com.magicrealm.utils.GameLog;

public class Peer extends Table {
	
	@Override
	public void one() {
		nothing();
	}

	@Override
	public void two() {
		five();
		secretPath();
	}

	@Override
	public void three() {
		four();
		secretPath();
	}

	@Override
	public void four() {
		GameLog.log("No hidden enemies here");
	}

	@Override
	public void five() {
		clues();
	}

	@Override
	public void six() {
		nothing();
	}

}
