package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.ActionChit;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;


public class Rest extends Activity {
	
	public Rest() {
		this.activity = ActivityType.REST;
	}

	@Override
	public void execute(ServerGameState gameState, String clientId) {
		MRCharacter character = gameState.getCharacter(clientId);
		IClientService clientService = gameState.getClientService(clientId);
		
		// old stuff
		if(character.getFatiguedChits().length > 0) {
			ActionChit restChit = (ActionChit) clientService.clientSelect(character.getFatiguedChits(), "Select a chit to restore");
//			SelectObject restChit = new SelectObject(character.getFatiguedChits());
//			int option = JOptionPane.showConfirmDialog(null, restChit, "Select A Chit To Restore", JOptionPane.OK_CANCEL_OPTION);
//			if(option == JOptionPane.OK_OPTION) {
			character.rest(restChit);
//			}
		} else {
			clientService.sendMessage("No action chits to rest");
		}
	}
}
