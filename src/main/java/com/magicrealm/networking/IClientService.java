package com.magicrealm.networking;

import com.magicrealm.characters.MRCharacter;
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
	 * Notify the client that the game has started
	 */
	public void gameStarted(MagicRealmHexEngineModel model);
	/**
	 * Notify the client that the birdsong stage has started
	 * @param model 
	 */
	public void birdsongStarted(MagicRealmHexEngineModel model);

	/**
	 * Notify the client that the daylight stage has started
	 */
	public abstract void daylightStarted();
	
	/**
	 * Notify the client that the evening stage has started
	 */
	public void eveningStarted();

	/**
	 * Displays a message to the client
	 * @param message
	 */
	public void sendMessage(String message);
	
	/**
	 * Prompts client to select an item
	 * @param objArray
	 * @param message
	 * @return selected item from array
	 */
	public Object clientSelect(Object[] objArray, String message);
	public Object clientSelect(Object[] objArray, String message, int type);

	public Object test(String string);

	public void updateBoard(MagicRealmHexEngineModel board);

	public void updateCharacter(MRCharacter character);
}
