package com.magicrealm.networking;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.Config;



class Net implements INet {
	
	private final Log log = LogFactory.getLog(Net.class);

	private final String clientId;
	private ServerGameState gameState;

	public Net(String clientId, ServerGameState gameState) {
		this.clientId = clientId;
		this.gameState = gameState.getInstance();
	}

	/*
	 * BEGIN TEST METHODS
	 */
	@Override
	public String test() {
		return "Testing 123";
	}

	@Override
	public String test(String string, Object obj) {
		return string + obj;
	}

	@Override
	public void testThrow() throws NullPointerException {
		throw new NullPointerException();
	}

	@Override
	public Object test(int integer) {
		return integer;
	}
	/*
	 * END TEST METHODS
	 */
	
	public String getClientId() {
		return clientId;
	}
	
	public ServerGameState getGameState() {
		return gameState;
	}

	@Override
	public MRCharacter selectCharacter(String characterName) throws CharacterAlreadyTakenException {
		// throw error if the character is already chosen
		Collection<MRCharacter> characters = getGameState().getCharacters();
		for (MRCharacter mrCharacter : characters) {
			if (characterName.equals(mrCharacter.getName())) {
				throw new CharacterAlreadyTakenException(characterName + " is already taken by another player");
			}
		}

		log.info("Player " + getClientId() + " selected character " + characterName);
		
		// add the character, return the new character
		MRCharacter chosenCharacter = MRCharacter.getCharacterFromString(characterName);
		getGameState().addCharacter(getClientId(), chosenCharacter);
		return chosenCharacter;
	}

	@Override
	public boolean setCheatModeForCharacter(boolean cheatModeEnabled) {
		if (Config.getBoolean("cheatModeEnabled", false)) {
			log.info("Cheat mode set to " + cheatModeEnabled + " for player " + getClientId());
			getGameState().getCharacter(getClientId()).setCheatModeEnabled(cheatModeEnabled);
			return true;
			
		} else {
			// failed to turn cheat mode on or off
			return false;
		}
	}
	
}