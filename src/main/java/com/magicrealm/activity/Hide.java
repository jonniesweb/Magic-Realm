package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.GameLog;
import com.magicrealm.utils.ProbabilityCalculator;

public class Hide extends Activity {
	
	public Hide() {
		this.activity = ActivityType.HIDE;
	}

	@Override
	public void execute(ServerGameState gameState, String clientId) {
		MRCharacter character = gameState.getCharacter(clientId);
		IClientService clientService = gameState.getClientService(clientId);
		
		if(ProbabilityCalculator.calculateHide(clientId)) {
			character.hide();
			clientService.sendMessage("Character Hidden");
		}
		else
			clientService.sendMessage("Character failed to hide");
	}

}
