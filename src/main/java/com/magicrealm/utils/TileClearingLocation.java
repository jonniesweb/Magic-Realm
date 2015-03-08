package com.magicrealm.utils;

import java.io.Serializable;

import com.magicrealm.models.tiles.GameTile.TileType;

/**
 * Keep track of the location of a clearing by storing the {@link TileType} and
 * the clearing number.
 */
public class TileClearingLocation implements Serializable {
	
	private static final long serialVersionUID = -1182441900203897651L;
	
	private TileType tileType;
	private int clearingNumber;
	
	/**
	 * @param tileType
	 * @param clearingNumber
	 */
	public TileClearingLocation(TileType tileType, int clearingNumber) {
		this.tileType = tileType;
		this.clearingNumber = clearingNumber;
	}
	
	public TileType getTileType() {
		return tileType;
	}
	
	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	public int getClearingNumber() {
		return clearingNumber;
	}
	
	public void setClearingNumber(int clearingNumber) {
		this.clearingNumber = clearingNumber;
	}
	
	public String toString() {
		return tileType.name()+clearingNumber;
	}
	
}
