package com.magicrealm.models.tiles;

import java.util.HashMap;
import java.util.Map;

import com.magicrealm.models.Clearingable;


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

	/**
	 * Initializes the clearing based on the type of tile. <br>
	 * <br>
	 * <b>Limitations: Doesn't support caves or the tile having magic at the
	 * moment.
	 * 
	 * @param type
	 */
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
		case BV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(2, 1);
			addExit(4, 2, 3);
			addExit(5, 4);
			addExit(1, 5);
			break;
		case CV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(1, 3);
			addExit(2, 2);
			addExit(5, 4);
			addExit(4, 1, 5);
			break;
		case DV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(4, 1, 2);
			addExit(2, 3);
			addExit(1, 4);
			addExit(5, 5);
			break;
		case EV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(1, 1);
			addExit(2, 2);
			addExit(4, 3, 4);
			addExit(5, 5);
			break;
		case LW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(2, 1, 5);
			addExit(4, 2);
			addExit(5, 3, 4);
			break;
		case MW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(5, 1, 2);
			addExit(2, 3, 4);
			addExit(4, 5);
			break;
		case NW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(5, 1, 5);
			addExit(2, 2, 3);
			addExit(4, 4);
			break;
		case OW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(4, 1);
			addExit(5, 2, 3);
			addExit(2, 4, 5);
			break;
		case PW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(2, 1, 2);
			addExit(4, 3);
			addExit(5, 4, 5);
			break;
		case B:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 6);
			addPath(2, 3);
			addPath(3, 5);
			addPath(3, 6);
			addSecretPath(4, 5);
			addPath(4, 6);
			addExit(1, 1, 3);
			addExit(2, 4, 0);
			addExit(4, 5);
			addExit(5, 2);
			break;
		case CN:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 3);
			addPath(2, 3);
			addPath(3, 6);
			addPath(4, 5);
			addPath(4, 6);
			addSecretPath(3, 5);
			addSecretPath(1, 4);
			addExit(1, 4);
			addExit(2, 3);
			addExit(5, 2);
			break;
		case CS:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 6);
			addPath(6, 4);
			addPath(4, 2);
			addPath(3, 5);
			addSecretPath(2, 3);
			addExit(2, 1);
			addExit(5, 2);
			addExit(1, 5);
			break;
		case HP:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(5, 1);
			addPath(1, 4);
			addPath(4, 2);
			addPath(3, 6);
			addExit(5, 0);
			addExit(6, 2);
			addExit(3, 4);
			break;
		case R:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 2);
			addPath(1, 4);
			addPath(4, 6);
			addPath(3, 6);
			addPath(3, 5);
			addSecretPath(1, 5);
			addExit(5, 1);
			addExit(1, 2);
			addExit(2, 3, 4);
			addExit(3, 5);
			break;
		case CF:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(2, 3);
			addPath(3, 5);
			addPath(1, 6);
			addPath(6, 4);
			addSecretPath(2, 5);
			addSecretPath(3, 6);
			addExit(2, 1);
			addExit(1, 2);
			addExit(4, 4);
			addExit(5, 5);
			break;
		case CG:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(2, 5);
			addPath(5, 3);
			addPath(3, 6);
			addPath(4, 1);
			addSecretPath(2, 3);
			addSecretPath(1, 6);
			break;
		case DW:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 6);
			addPath(6, 4);
			addPath(4, 5);
			addPath(5, 3);
			addPath(3, 2);
			addSecretPath(1, 4);
			addSecretPath(3, 6);
			addExit(5, 1);
			addExit(1, 2, 3);
			addExit(2, 4, 5);
			break;
		case L:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(2, 5);
			addPath(4, 1);
			addPath(1, 6);
			addPath(6, 3);
			addSecretPath(4, 6);
			addSecretPath(1, 3);
			addExit(5, 2);
			addExit(4, 3);
			addExit(2, 4);
			addExit(3, 5);
			break;
		case M:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(1, 3);
			addPath(3, 6);
			addPath(6, 5);
			addPath(5, 2);
			addPath(2, 4);
			addSecretPath(4, 6);
			addExit(2, 1);
			addExit(4, 3);
			addExit(5, 5);
			break;
		default:
			throw new RuntimeException();
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
	
	/**
	 * Add a secret path between two clearings
	 * @param clearing1
	 * @param clearing2
	 */
	private void addSecretPath(int clearing1, int clearing2) {
		clearings.get(clearing1).addSecretPath(clearings.get(clearing2));
		clearings.get(clearing2).addSecretPath(clearings.get(clearing1));
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

	public TileClearing getClearing(int clearingNumber) {
		return clearings.get(clearingNumber);
	}
	
	public void addToClearing(int clearingNumber, Clearingable clearingable) {
		getClearing(clearingNumber).addToClearing(clearingable);
	}
	
	
}
