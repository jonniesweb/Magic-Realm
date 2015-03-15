package com.magicrealm.networking;

import com.magicrealm.models.board.MagicRealmHexEngineModel;


/**
 * Operations to be invoked on the client-side.
 */
public interface IClientService {

	/**
	 * Just for testing. Can be removed.
	 */
	public int getDiceRoll();

	/**
	 * Notify the client that the birdsong stage has started
	 */
	public void birdsongStarted();

	/**
	 * Notify the client that the daylight stage has started
	 */
	public abstract void daylightStarted();

	/**
	 * Displays a message to the client
	 * @param message
	 */
	void sendMessage(String message);
	
	public Object clientSelect(Object[] objArray, String message);

	public Object test(String string);

	public void updateBoard(MagicRealmHexEngineModel board);
}
