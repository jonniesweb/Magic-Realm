package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;

public class Hide extends Activity {
	
	public Hide() {
		this.activity = ActivityType.HIDE;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		player.attemptHide();
	}

}
