package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.GameState;
import com.magicrealm.gui.SelectObject;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.models.tiles.TileClearing.ClearingType;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public abstract class Table implements TableMethods {
	
	
	public enum Peer {CHOICE, CLUES_PATHS, ENEMIES_PATHS, ENEMIES, CLUES, NOTHING};
	public enum Locate {CHOICE, PASSAGES_CLUES, PASSAGES, DISCOVER, NOTHING};
	
	public void execute(Result result) {
		switch (result) {
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
		TileClearing playerTile = GameState.getInstance().getModel().findChit(GameState.getInstance().getCharacter());
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
			print("You Discovered a secret path, this path is now accessible");
		} else {
			print("no secret paths");
		}
	}
	
	public ClearingMapChit getChitAtLocation() {
		ClearingMapChit chit = GameState.getInstance().getModel().findChitTile(GameState.getInstance().getCharacter()).getSiteSoundChit();
		if(chit != null && chit.getClearing() == getClearing().getClearingNumber()) {			
			return chit;
		}
		return null;
	}
	
	public void clues() {
		ClearingMapChit chit = getChitAtLocation();
		if(chit != null) {			
			print("You found clues of " + chit.getType());
		} else {
			print("You found nothing of value");
		}
	}
	
	public void nothing() {
		print("Nothing");
	}
	
	public void print(String s) {
		System.out.println(s);
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
