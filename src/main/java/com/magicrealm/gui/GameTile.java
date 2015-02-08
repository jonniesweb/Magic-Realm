package com.magicrealm.gui;


public class GameTile {
	
	public enum TileType {
		AV, BV, CV, DV, EV, LW, MW, NW, OW, PW, B, CN, CS, HP, R, CF, CG, DW, L, M
	};

	private TileType tileType;
	private int rotation;

	public GameTile(TileType type, int rotation) {
		this.tileType = type;
		this.rotation = rotation;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(byte rotation) {
		this.rotation = rotation;
	}
	
	
}
