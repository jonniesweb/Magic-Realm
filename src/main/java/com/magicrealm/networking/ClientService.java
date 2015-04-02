package com.magicrealm.networking;

import java.rmi.RemoteException;

import com.magicrealm.client.ClientGameState;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.gui.UIMediator;
import com.magicrealm.models.BirdsongActivities;
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
	public void gameStarted(MagicRealmHexEngineModel model) {
			// hide the StartGameFrame if its open
		if (getGameState().getStartGameFrame() != null)
			getGameState().getStartGameFrame().setVisible(false);
		
		// show the board
		gameState.setModel(model);
		setView(model);

	}

	protected void setView(MagicRealmHexEngineModel model) {
		UIMediator uiMediator = new UIMediator();
		uiMediator.createWidgets(model);
		gameState.setUiMediator(uiMediator);
	}

	@Override
	public void birdsongStarted(MagicRealmHexEngineModel model) {
		gameState.setModel(model);
		
		// initialize the birdsong activities
		getGameState().setActivities(new BirdsongActivities(model.getChitLocation(getGameState().getCharacter())));
		sendMessage("Birdsong has begun");
	}

	@Override
	public void daylightStarted() {
		sendMessage("Daylight has begun");
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

	@Override
	public void eveningStarted() {
		// TODO Auto-generated method stub
		sendMessage("Evening has begun");
	}
}
