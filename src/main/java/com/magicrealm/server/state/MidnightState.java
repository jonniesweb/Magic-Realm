package com.magicrealm.server.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.models.monsters.Ghost;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class MidnightState extends ServerState {

	public MidnightState(ServerGameState instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		for (IClientService clientService : getGameState().getClientServices()) {
			clientService.midnightStarted();
		}
		
		if(getDay() % 7 == 0) {
			regenerateMonsters();
		}
		if(getDay() >= 28) {
			endGame();
		}
		
		changePhase();
		
		updateClients();
	}
	
	public void endGame() {
		Iterator<MRCharacter> it = getGameState().getCharacters().iterator();
		List<PointHolder> pointholders = new ArrayList<MidnightState.PointHolder>();
		int points = 0;
		while(it.hasNext()) {
			MRCharacter character = it.next();
			points += character.getFame() /10;
			points += character.getNotoriety() / 20;
			points += character.getGold() / 30;
			
			pointholders.add(new PointHolder(character.getCharacterType(), points));
		}
		
		Collections.sort(pointholders);
		Collections.reverse(pointholders);
		
		for (IClientService clientService : getGameState().getClientServices()) {
			for(PointHolder ph: pointholders) {
				clientService.sendMessage(ph.type.toString() + " has " + ph.points + " points");
			}
		}
		
		// TODO still need to actually end the game
	}
	
	public void regenerateMonsters() {
	
		getGameState().getBoard().resetMonsters();
		
		// hard code for now
		getGameState().getBoard().placeChit(TileType.EV, 5, new Ghost());
		getGameState().getBoard().placeChit(TileType.EV, 5, new Ghost());
	}
	
	public void changePhase() {
		// switch state to birdsong
		BirdsongState birdsongState = new BirdsongState(getGameState());
		getGameState().setState(birdsongState);
		birdsongState.init();
	}
	
	private class PointHolder implements Comparable<PointHolder> {
		
		public CharacterType type;
		public int points = 0;
		
		public PointHolder(CharacterType characterType, int points) {
			// TODO Auto-generated constructor stub
			this.type = characterType;
			this.points = points;
		}

		@Override
		public int compareTo(PointHolder o) {
			// TODO Auto-generated method stub
			return new Integer(points).compareTo(new Integer(o.points));
		}
		
	}

}
