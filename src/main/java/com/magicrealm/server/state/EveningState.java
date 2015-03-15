package com.magicrealm.server.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.TileClearingLocation;

public class EveningState extends ServerState {
	
	public Set<TileClearingLocation> relevantLocations = new HashSet<TileClearingLocation>();

	public EveningState(ServerGameState instance) {
		super(instance);
		Set<MRCharacter> characters = getGameState().getCharacters();
		Iterator<MRCharacter> it = characters.iterator();
		while(it.hasNext()) {
			relevantLocations.add(getGameState().getBoard().getChitLocation(it.next()));
		}
	}
	
	public void combatPhase() {
		ArrayList<TileClearingLocation> locations = new ArrayList<TileClearingLocation>();
		Collections.shuffle(locations);
		
		while(!locations.isEmpty()) {			
			TileClearing clearing = getGameState().getBoard().getClearing(locations.remove(0));
			List<Placeable> chits = clearing.getChits();
			
		}
		
		
	}

}
