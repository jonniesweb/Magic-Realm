package com.magicrealm.networking;

import java.rmi.RemoteException;

import com.magicrealm.client.ClientGameState;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
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

	@Override
	public Object clientSelect(Object[] objArray, String message) {
		SimpleSelection selectObj = new SimpleSelection(objArray, message);
		return selectObj.getSelected();
	}
	
	@Override
	public void updateBoard(MagicRealmHexEngineModel board) {
		gameState.setModel(board);
	}
}
