package com.magicrealm.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.state.PlayerConnectState;
import com.magicrealm.server.state.ServerState;

/**
 * Singleton class for all game state.
 */
public class ServerGameState {
	
	private static ServerGameState instance;
	
	private MagicRealmHexEngineModel board;
	private Map<String, User> characters = new HashMap<>();
	private ServerState state = new PlayerConnectState(this);
	private boolean cheatMode = false;
	
	protected ServerGameState() {
	}
	
	public void setBoard(MagicRealmHexEngineModel board) {
		this.board = board;
	}
	
	public MagicRealmHexEngineModel getBoard() {
		return board;
	}
	
	public Set<MRCharacter> getCharacters() {
		Set<MRCharacter> set = new HashSet<>();
		for (User user : characters.values()) {
			if (user.character != null) {
				set.add(user.character);
			}
		}
		return set;
	}
	
	public Set<IClientService> getClientServices() {
		HashSet<IClientService> set = new HashSet<>();
		for (User user : characters.values()) {
			if (user.clientService != null) {
				set.add(user.clientService);
			}
		}
		return set;
	}
	
	public MRCharacter getCharacter(String clientId) {
		return characters.get(clientId).character;
	}
	
	public IClientService getClientService(String clientId) {
		return characters.get(clientId).clientService;
	}
	
	public void addCharacter(String clientId, MRCharacter character) {
		if (characters.get(clientId) == null) {
			characters.put(clientId, new User());
		}
		characters.get(clientId).character = character;
	}
	
	public static ServerGameState getInstance() {
		if (instance == null) {
			instance = new ServerGameState();
		}
		return instance;
	}

	public ServerState getState() {
		return state;
	}
	
	public void setState(ServerState state) {
		this.state = state;
	}

	public void addClientService(String clientId, IClientService service) {
		if (characters.get(clientId) == null) {
			characters.put(clientId, new User());
		}
		characters.get(clientId).clientService = service;
	}
	
	public boolean isCheatMode() {
		return cheatMode;
	}

	public void setCheatMode(boolean cheatMode) {
		this.cheatMode = cheatMode;
	}

	private class User {
		IClientService clientService;
		MRCharacter character;
	}
	
	public int getNumberOfPlayers() {
		return characters.size();
	}

}
