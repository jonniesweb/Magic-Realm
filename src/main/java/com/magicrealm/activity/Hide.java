package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;

import com.magicrealm.utils.GameLog;
import com.magicrealm.utils.ProbabilityCalculator;

public class Hide extends Activity {
	
	public Hide() {
		this.activity = ActivityType.HIDE;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		if(ProbabilityCalculator.calculateHide()) {
			player.hide();
			GameLog.log("Character Hidden");
		}
		else
			GameLog.log("Character failed to hide");
	}

}
