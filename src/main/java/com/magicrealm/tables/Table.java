package com.magicrealm.tables;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.SiteChit.Site;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.models.tiles.TileClearing.ClearingType;
import com.magicrealm.server.ServerGameState;
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
		MRCharacter character = getGameState().getCharacter(getClientId());
		TileClearing playerTile = getGameState().getBoard().getChitClearing(character);
		if((Object) this instanceof Peer && playerTile.getClearingType() == ClearingType.MOUNTAIN) {
			return (TileClearing) getGameState().getClientService(getClientId()).clientSelect(playerTile.getPlayerConnectedClearings(character), "Select A Clearing to Peer");
		}
		return playerTile;
	}
	
	public void secretPath() {
		TileClearing secret = getClearing().getConnectedSecretClearing();
		if(secret != null) {
			getGameState().getCharacter(getClientId()).addDiscovery(secret);
			gameState.getClientService(clientId).sendMessage("You Discovered a secret path, this path is now accessible");
		} else {
			gameState.getClientService(clientId).sendMessage("no secret paths");
		}
	}
	
	public ClearingMapChit getChitAtLocation() {
		MRCharacter character = getGameState().getCharacter(getClientId());
		ClearingMapChit chit = getGameState().getBoard().getChitTile(character).getSiteSoundChit();
		if(chit == null) {
			return null;
		} else if(chit instanceof SiteChit) {
			SiteChit site = (SiteChit) chit;
			if(site.getSiteType() == Site.lost_castle || site.getSiteType() == Site.lost_city) {
				for(MapChit c: site.getExtraChits()) {
					if(c instanceof SiteChit) {
						SiteChit sc = (SiteChit) c;
						if(sc.getClearing() == getClearing().getClearingNumber()) {
							return sc;
						}
					}
				}
			} else {
				if(site.getClearing() == getClearing().getClearingNumber()) {
					return site;
				}
			}
		}
		
		if(chit != null && chit.getClearing() == getClearing().getClearingNumber()) {			
			return chit;
		}
		return null;
	}
	
	public void clues() {
		ClearingMapChit chit = getChitAtLocation();
		if(chit != null) {			
			gameState.getClientService(clientId).sendMessage("You found clues of " + chit.getType());
		} else {
			gameState.getClientService(clientId).sendMessage("You found nothing of value");
		}
	}
	
	public void nothing() {
		gameState.getClientService(clientId).sendMessage("Nothing");
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
