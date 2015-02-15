package com.magicrealm.gui;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * Each side of the tile is represented by a number. 0 to 5 going clockwise,
 * starting with 0 at the bottom.
 * <pre>
 *        3
 *       ____
 *    2 /    \ 4
 *     /      \
 *     \      /
 *   1  \____/ 5
 *        0
 * </pre>
 */
public class GameTile {
	
	public enum TileType {
		AV, BV, CV, DV, EV, LW, MW, NW, OW, PW, B, CN, CS, HP, R, CF, CG, DW, L, M
	};

	/**
	 * The type of this tile.
	 */
	private TileType tileType;
	
	/**
	 * Number between 0 and 5 representing 60 degree increments of rotation
	 * clockwise. 0 represents no rotation, resulting in the tile's text to be
	 * along side 0.
	 */
	private int rotation;
	/**
	 * Map of clearings that each tile has.
	 * Clearing number followed by the clearing.
	 */
	private Map<Integer, TileClearing> clearings = new HashMap<Integer, TileClearing>();
	
	/**
	 * The paths that exit the tile along one of the sides.
	 * Clearing side maps to the TileClearing that connects to it.
	 */
	private Map<Integer, TileClearing> tileExits = new HashMap<Integer, TileClearing>();;

	public GameTile(TileType type, int rotation) {
		this.tileType = type;
		this.rotation = rotation;
		
		initClearings(type);
	}

	private void initClearings(TileType type) {
		switch (type) {
		case AV:
			addClearing(1, 2, 4, 5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(2, 1);
			addExit(1, 2);
			addExit(5, 3);
			addExit(3, 4, 5);
			break;
//		case BV:
//			return "badvalley";
//		case CV:
//			return "curstvalley";
//		case DV:
//			return "darkvalley";
//		case EV:
//			return "evilvalley";
//		case LW:
//			return "lindenwoods";
//		case MW:
//			return "maplewoods";
//		case NW:
//			return "nutwoods";
//		case OW:
//			return "oakwoods";
//		case PW:
//			return "pinewoods";
//		case B:
//			return "borderland";
//		case CN:
//			return "cavern";
//		case CS:
//			return "caves";
//		case HP:
//			return "highpass";
//		case R:
//			return "ruins";
//		case CF:
//			return "cliff";
//		case CG:
//			return "crag";
//		case DW:
//			return "deepwoods";
//		case L:
//			return "ledges";
//		case M:
//			return "mountain";
		default:
			break;
//			throw new RuntimeException();
		}
	}

	/**
	 * Mark a clearing that has a path leading to one of the edges
	 * @param clearingNumber
	 * @param connectsToSide a list of tile sides that the clearing connects to
	 */
	private void addExit(int clearingNumber, int ... connectsToSide) {
		TileClearing clearing = clearings.get(clearingNumber);
		for (int i : connectsToSide) {
			tileExits.put(i, clearing);
		}
	}

	/**
	 * Add a normal path between two clearings
	 * @param clearing1 Undirected connection to j
	 * @param clearing2 Undirected connection to i
	 */
	private void addPath(int clearing1, int clearing2) {
		clearings.get(clearing1).addPath(clearings.get(clearing2));
		clearings.get(clearing2).addPath(clearings.get(clearing1));
	}

	private void addClearing(int ... i) {
		for (int j : i) {
			clearings.put(j, new TileClearing());
		}
		
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

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
//	public void addChitToClearing(Chit chit) {
//		
//	}
	
	
}
