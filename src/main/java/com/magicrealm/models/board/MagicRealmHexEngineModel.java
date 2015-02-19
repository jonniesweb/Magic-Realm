package com.magicrealm.models.board;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.HexEngineModel;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.Clearingable;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;

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
	
	
	
	
}
