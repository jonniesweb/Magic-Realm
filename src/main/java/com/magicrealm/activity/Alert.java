package com.magicrealm.activity;

import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class Alert extends Activity {
	
	public Alert() {
		this.activity = ActivityType.ALERT;
	}

	@Override
	public void execute(ServerGameState gameState, String clientId) {
		// TODO Auto-generated method stub
		IClientService client = gameState.getClientService(clientId);
		String state = (String) client.clientSelect(new String[]{"Alert",  "Unalert"}, "Choose a state for your weapon");
		if(state == "Alert") {
			gameState.getCharacter(clientId).getActiveWeapon().alert();
			client.sendMessage("Weapon alerted");
		} else {
			gameState.getCharacter(clientId).getActiveWeapon().sleep();
			client.sendMessage("Weapon not alerted");
		}
	}

}
