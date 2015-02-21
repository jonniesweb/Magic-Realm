package com.magicrealm.models.board;


import java.util.Map;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.HexEngineModel;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.Clearingable;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;

public class MagicRealmHexEngineModel implements HexEngineModel<GameTile> {
	
	/**
	 * Array to keep values
	 */
	private final GameTile[] array;
	
	/**
	 * Number of columns
	 */
	private final int columns;
	
	/**
	 * Number of rows
	 */
	private final int rows;
	
	private static final int[][][] directions = new int[][][] {
			{ { 0, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 } },
			{ { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 }, { 1, 1 } } };
	
	/**
	 * @param columns
	 * @param rows
	 */
	public MagicRealmHexEngineModel(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		
		array = new GameTile[columns * rows];
	}
	
	@Override
	public int getColumnNumber() {
		return this.columns;
	}
	
	@Override
	public int getRowNumber() {
		return this.rows;
	}
	
	@Override
	public GameTile getValueAt(final int col, final int row) {
		if (isPositionValid(col, row)) {
			return this.array[col + row * this.columns];
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean isPositionValid(final int col, final int row) {
		return col >= 0 && row >= 0 && col < this.columns && row < this.rows;
	}
	
	@Override
	public boolean isPositionValid(final HexPosition pos) {
		return this.isPositionValid(pos.getColumn(), pos.getRow());
	}
	
	@Override
	public GameTile getValueAt(final HexPosition pos) {
		return this.getValueAt(pos.getColumn(), pos.getRow());
	}
	
	@Override
	public void setValueAt(final HexPosition pos, final GameTile value) {
		this.setValueAt(pos.getColumn(), pos.getRow(), value);
	}
	
	@Override
	public void setValueAt(final int col, final int row, final GameTile value) {
		if (value == null) {
			throw new NullPointerException("Can't have null value");
		}
		if (isPositionValid(col, row)) {
			this.array[col + row * this.columns] = value;
		} else {
			throw new RuntimeException("Position out of bounds");
		}
	}
	
	@Override
	public void attachedToEngine(HexEngine<?> engine) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void detachedFromEngine(HexEngine<?> engine) {
		// TODO Auto-generated method stub
		
	}
	
	public void placeChit(TileType tile, int clearingNumber, Clearingable clearingable) {
		GameTile gameTile = getTile(tile);
		gameTile.addToClearing(clearingNumber, clearingable);
	}
	
	public GameTile getTile(TileType tile) {
		for (GameTile gameTile : array) {
			if (gameTile != null && gameTile.getTileType().equals(tile)) {
				return gameTile;
			}
		}
		return null;
	}
	
	/**
	 * Get the hex tile adjacent to <code>edge</code> side. If it doesn't exist,
	 * return null.
	 * 
	 * Source: http://www.redblobgames.com/grids/hexagons/#neighbors
	 * @param tile The tile to find the adjacent edge of
	 * @param edge The side where the tile to get is touching
	 * @return
	 */
	public GameTile getTileAdjacentToEdge(GameTile tile, int edge) {
		HexPosition location = getLocation(tile);
		
		int col = location.getColumn();
		int row = location.getRow();
		if ((location.getColumn() & 1) == 0) {
			// even
			col += directions[0][edge][0];
			row += directions[0][edge][1];
		} else {
			// odd
			col += directions[1][edge][0];
			row += directions[1][edge][1];
		}
		return getValueAt(col, row);
	}
	
	public HexPosition getLocation(GameTile tile) {
		// find index position
		int i = -1;
		for (GameTile gameTile : array) {
			i++;
			if (gameTile != null && gameTile.equals(tile)) {
				break;
			}
		}
		
		int col = i % getColumnNumber();
		int row = i / getColumnNumber();
		
		return new HexPosition(col, row);
		
	}
	
	public static int getOppositeEdge(int edge) {
		switch (edge) {
		case 0:
			return 3;
		case 1:
			return 4;
		case 2:
			return 5;
		case 3:
			return 0;
		case 4:
			return 1;
		case 5:
			return 2;
		default:
			throw new RuntimeException("Illegal edge value");
		}
	}

	protected void connectAllTheThings() {
		
		// iterate over each tile on the board
		for (GameTile gameTile : array) {
			if (gameTile == null)
				continue;
			
			// look at each exit of the tile
			Map<Integer, TileClearing> exits = gameTile.getTileExits();
			for (int side : exits.keySet()) {
				
				int outsideEdge = gameTile.rotatedToNormal(side);
				
				// get the adjacent tile
				GameTile adjacentTile = getTileAdjacentToEdge(gameTile, outsideEdge);
				if (adjacentTile == null)
					continue;
				
				
				// get the clearing of the adjacent tile with the exit that
				// meets up with this tile's exit
				TileClearing oppositeClearing = adjacentTile.getClearingOnSide(getOppositeEdge(outsideEdge));
				TileClearing thisClearing = exits.get(side);
				
				thisClearing.addPath(oppositeClearing);
				oppositeClearing.addPath(thisClearing);
			}
		}
		
	}
	
}
