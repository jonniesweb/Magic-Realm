package com.magicrealm.networking;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.exceptions.GameAlreadyStartedException;
import com.magicrealm.exceptions.InappropriateStateException;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.MRCharacter.character;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.server.state.PlayerConnectState;
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
	public MRCharacter selectCharacter(character characterType) throws CharacterAlreadyTakenException {
		
		if (!(getGameState().getState() instanceof PlayerConnectState)) {
			throw new InappropriateStateException("select character", getGameState().getState().getClass());
		}
		
		// throw error if the character is already chosen
		Collection<MRCharacter> characters = getGameState().getCharacters();
		for (MRCharacter mrCharacter : characters) {
			if (characterType.equals(mrCharacter.getName())) {
				throw new CharacterAlreadyTakenException(characterType + " is already taken by another player");
			}
		}

		log.info("Player " + getClientId() + " selected character " + characterType);
		
		// add the character, return the new character
		MRCharacter chosenCharacter = MRCharacter.createCharacter(characterType);
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

	@Override
	public void startGame() throws GameAlreadyStartedException {
		if (gameState.getState() instanceof PlayerConnectState) {
			PlayerConnectState playerConnectState = (PlayerConnectState) gameState.getState();
			playerConnectState.startGame();
		} else {
			throw new GameAlreadyStartedException();
		}
	}

	@Override
	public MagicRealmHexEngineModel getGameBoard() {
		return getGameState().getBoard();
	}

	@Override
	public MRCharacter setStartingLocationForCharacter(dwelling dwellingType) {
		MRCharacter character = gameState.getCharacter(clientId);
		character.setStartingLocation(dwellingType);
		return character;
	}
	
}