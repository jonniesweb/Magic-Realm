package com.magicrealm.networking;

import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.models.MRCharacter;


interface INet {
	
	// For testing
	public String test();
	public String test(String string, Object obj);
	public void testThrow() throws NullPointerException;
	public Object test(int integer);
	/**
	 * Select a character to play as. Cannot choose a character that is already
	 * chosen by another player.
	 * @param character
	 * @return The character the player has chosen
	 * @throws CharacterAlreadyTakenException
	 */
	public MRCharacter selectCharacter(String characterName)
			throws CharacterAlreadyTakenException;
	
	/**
	 * Set whether the player should be able to use cheat mode or not. Only sets
	 * it for the current player.
	 * @param cheatModeEnabled
	 * @return true if successful, false if unsuccessful
	 */
	public boolean setCheatModeForCharacter(boolean cheatModeEnabled);
}