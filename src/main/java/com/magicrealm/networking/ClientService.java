package com.magicrealm.networking;

import java.rmi.RemoteException;

import com.magicrealm.models.ClientGameState;
import com.magicrealm.utils.GameLog;

/**
 * Implementation of {@link IClientService} for client-side actions that are
 * called by the server
 */
public class ClientService implements IClientService {
	
	private ClientGameState gameState;
	
	/**
	 * @param gameState
	 */
	public ClientService(ClientGameState gameState) throws RemoteException {
		this.gameState = gameState;
	}
	
	public ClientGameState getGameState() {
		return gameState;
	}
	
	@Override
	public int getDiceRoll() {
		return 0;
	}


	@Override
	public void birdsongStarted() {
		
	}

	@Override
	public void daylightStarted() {
		
	}
	
	@Override
	public void sendMessage(String message) {
		GameLog.log(message);
	}

	@Override
	public Object test(String string) {
		return string + 123;
	}
	
}
