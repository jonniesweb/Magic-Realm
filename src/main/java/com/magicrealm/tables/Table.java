package com.magicrealm.tables;

import com.magicrealm.GameState;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.models.tiles.TileClearing.ClearingType;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.GameLog;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public abstract class Table implements TableMethods {
	
	
	private ServerGameState gameState;
	private String clientId;

	public enum Peer {CHOICE, CLUES_PATHS, ENEMIES_PATHS, ENEMIES, CLUES, NOTHING};
	public enum Locate {CHOICE, PASSAGES_CLUES, PASSAGES, DISCOVER, NOTHING};
	
	public Table(ServerGameState gameState, String clientId) {
		this.gameState = gameState;
		this.clientId = clientId;
		
	}
	
	public void execute(Result result) {
		switch (result) {
		case ONE:
			this.one();
			break;
		case TWO:
			this.two();
			break;
		case THREE:
			this.three();
			break;
		case FOUR:
			this.four();
			break;
		case FIVE:
			this.five();
			break;
		case SIX:
			this.six();
			break;
		default:
			break;
		}
	}
	
	public TileClearing getClearing() {
		TileClearing playerTile = GameState.getInstance().getModel().getCharacterClearing();
		if((Object) this instanceof Peer && playerTile.getClearingType() == ClearingType.MOUNTAIN) {
			SimpleSelection selectClearing = new SimpleSelection(playerTile.getPlayerConnectedClearings(), "Select A Clearing to Peer");
			return (TileClearing) selectClearing.getSelected();
		}
		return playerTile;
	}
	
	public void secretPath() {
		TileClearing secret = getClearing().getConnectedSecretClearing();
		if(secret != null) {
			GameState.getInstance().getCharacter().addDiscovery(secret);
			GameLog.log("You Discovered a secret path, this path is now accessible");
		} else {
			GameLog.log("no secret paths");
		}
	}
	
	public ClearingMapChit getChitAtLocation() {
		ClearingMapChit chit = GameState.getInstance().getModel().getChitTile(GameState.getInstance().getCharacter()).getSiteSoundChit();
		if(chit != null && chit.getClearing() == getClearing().getClearingNumber()) {			
			return chit;
		}
		return null;
	}
	
	public void clues() {
		ClearingMapChit chit = getChitAtLocation();
		if(chit != null) {			
			GameLog.log("You found clues of " + chit.getType());
		} else {
			GameLog.log("You found nothing of value");
		}
	}
	
	public void nothing() {
		GameLog.log("Nothing");
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public ServerGameState getGameState() {
		return gameState;
	}
	
	public String getClientId() {
		return clientId;
	}

}
