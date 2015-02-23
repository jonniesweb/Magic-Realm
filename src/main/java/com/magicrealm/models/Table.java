package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.GameState;
import com.magicrealm.gui.SelectObject;
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
	
	public TileClearing getClearing(Table table) {
		TileClearing playerTile = GameState.getInstance().getModel().findChit(GameState.getInstance().getCharacter());
		if((Object) table instanceof Peer && playerTile.getClearingType() == ClearingType.MOUNTAIN) {
			SelectObject selectClearing = new SelectObject(playerTile.getConnectedClearings().toArray(new TileClearing[0]));
			JOptionPane.showConfirmDialog(null, selectClearing, "Select A Clearing", JOptionPane.OK_CANCEL_OPTION);
			return (TileClearing) selectClearing.getSelected();
		}
		return playerTile;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
