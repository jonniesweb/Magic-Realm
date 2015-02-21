package com.magicrealm.models;


public class Rest extends Activity {
	
	public Rest() {
		this.activity = ActivityType.REST;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		player.rest();
	}

}
