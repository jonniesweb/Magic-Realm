package com.magicrealm.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.state.PlayerConnectState;
import com.magicrealm.server.state.ServerState;

/**
 * Singleton class for all game state.
 */
public class ServerGameState {
	
	private final Log log = LogFactory.getLog(ServerGameState.class);
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
	
	public IClientService getClientService(CharacterType type) {
		for(User u: characters.values()) {
			if(u.character.characterType == type) {
				return u.clientService;
			}
		}
		return null;
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
	
	protected static void setInstance(ServerGameState state) {
		instance = state;
	}

	public ServerState getState() {
		return state;
	}
	
	public void setState(ServerState state) {
		ServerState old = this.state;
		this.state = state;
		log.info("Switching state from " + old + " to " + state);
	}

	public void addClientService(String clientId, IClientService service) {
		log.debug("adding client service for clientId: " + clientId);
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

	public class User {
		public IClientService clientService;
		public MRCharacter character;
	}
	
	public int getNumberOfPlayers() {
		return characters.size();
	}
	
	public List<String> getPlayerOrder() {
		// randomly determine the order that players take their turn.
		// for the future: create a player order strategy class for each player
		
		ArrayList<String> list = new ArrayList<>(characters.keySet());
		Collections.shuffle(list);
		return list;
	}
	
	public Collection<User> getAllUsers() {
		return characters.values();
	}
	
	/**
	 * Used in testing for resetting a singleton back to its new self.
	 */
	protected void reset() {
		
		getInstance().board = null;
		instance.characters = new HashMap<>();
		instance.state = new PlayerConnectState(this);
		instance.cheatMode = false;
	}

}
