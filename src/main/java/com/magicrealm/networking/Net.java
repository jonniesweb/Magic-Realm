package com.magicrealm.networking;

import java.util.Set;

import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.server.ServerGameState;



class Net implements INet {

	private final String client;

	public Net(String client) {
		this.client = client;
	}

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
	
	public String getClient() {
		return client;
	}
	
	public void selectCharacter(MRCharacter character) throws CharacterAlreadyTakenException {
		Set<MRCharacter> characters = ServerGameState.getCharacters();
		for (MRCharacter mrCharacter : characters) {
			if (character.getName().equals(mrCharacter.getName())) {
				throw new CharacterAlreadyTakenException(character.getName() + " is already taken by another player");
			}
		}
	}
	
}