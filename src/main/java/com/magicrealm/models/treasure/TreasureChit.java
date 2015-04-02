package com.magicrealm.models.treasure;

import java.io.Serializable;

public class TreasureChit implements Serializable {
	
	// 6 treasures within treasures
	// 23 large treasures
	// 45 small treasures
	
	public  enum treasure {
		treasures_within_treasures, large, small
	}
	
	private treasure treasureType;
	private int gold;
	private int fame;
	private int notoriety;

	/**
	 * @param treasureType
	 */
	public TreasureChit(treasure treasureType) {
		this.setTreasureType(treasureType);
	}
	
	public treasure getTreasureType() {
		return treasureType;
	}

	public void setTreasureType(treasure treasureType) {
		this.treasureType = treasureType;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getFame() {
		return fame;
	}

	public void setFame(int fame) {
		this.fame = fame;
	}

	public int getNotoriety() {
		return notoriety;
	}

	public void setNotoriety(int notoriety) {
		this.notoriety = notoriety;
	}
	
}
