package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.gui.SelectActivityPane;
import com.magicrealm.models.tiles.GameTile;

public abstract class Activity implements Executable {
	
	public enum ActivityType {MOVE, HIDE, REST, SEARCH};
	public ActivityType activity;
	
	public static Activity buildActivity(ActivityType type, SelectActivityPane activity) {
		GameTile tile;
		switch (type) {
		case MOVE:
			System.out.println("building move");
			tile = GameState.getInstance().getModel().getTile(activity.getTileType());
			return new Move(tile, tile.getClearing(activity.getClearingNumber()));
		case HIDE:
			return new Hide();
		case REST:
			return new Rest();
		case SEARCH:
			return new Search();
		}
		return null;
	}
	
	public String toString() {
		return activity.name().charAt(0)+"";
	}

}
