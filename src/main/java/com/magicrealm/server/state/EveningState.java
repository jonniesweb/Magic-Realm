package com.magicrealm.server.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Combat;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.monsters.MRMonster;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class EveningState extends ServerState {
	
	// Clearings with combat
	public Set<TileClearingLocation> combatLocations = new HashSet<TileClearingLocation>();
	private final Log log = LogFactory.getLog(EveningState.class);
	
	public EveningState(ServerGameState instance) {
		super(instance);
		Set<MRCharacter> characters = getGameState().getCharacters();
		Iterator<MRCharacter> it = characters.iterator();
		MagicRealmHexEngineModel board = getGameState().getBoard();
		while(it.hasNext()) {
			combatLocations.add(board.getChitLocation(it.next()));
		}
	}
	
	public void init() {
		for (IClientService clientService : getGameState().getClientServices()) {
			clientService.eveningStarted();
		}
		
		runCombatPhase();
		changePhase();
	}
	
	// Handles the combat phase for all clearings
	public void runCombatPhase() {
		List<TileClearingLocation> locations = new ArrayList<TileClearingLocation>(combatLocations);
		Collections.shuffle(locations);
		
		List<MRCharacter> characters = new ArrayList<MRCharacter>();
		List<MRMonster> monsters = new ArrayList<MRMonster>();
		
		Iterator it = locations.iterator();
		
		while(it.hasNext()) {			
			TileClearing clearing = getGameState().getBoard().getClearing((TileClearingLocation) it.next());
			List<Placeable> chits = clearing.getChits();
			
			for(Placeable p: chits) {
				if(p instanceof MRCharacter) {
					characters.add((MRCharacter) p);
				} else if(p instanceof MRMonster) {
					monsters.add((MRMonster) p);
				}
			}
			
			if(characters.size() + monsters.size() > 1) {
				for(MRCharacter c: characters) {
					IClientService client = getGameState().getClientService(c.getCharacterType());
					client.sendMessage("You have entered combat!");
				}
				new Combat(getGameState(), characters, monsters);
			}
			characters.clear();
		}
		
	}
	
	public void changePhase() {
		// switch state to birdsong
		MidnightState midnightState = new MidnightState(getGameState());
		getGameState().setState(midnightState);
		midnightState.init();
	}

}
