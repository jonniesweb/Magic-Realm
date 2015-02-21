package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.Collection;
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
	
	public static final int MAX_X = 497;
	public static final int MAX_Y = 431;

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
	private Map<Integer, TileClearing> tileExits = new HashMap<Integer, TileClearing>();

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
	protected void initClearings(TileType type) {
		switch (type) {
		case AV:
			addClearing(1, 2, 4, 5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(2, 1);
			addExit(1, 2);
			addExit(5, 3);
			addExit(3, 4, 5);
			setPosition(1, 153, 129);
			setPosition(2, 277, 129);
			setPosition(4, 306, 253);
			setPosition(5, 246, 88);
			break;
		case BV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(2, 1);
			addExit(4, 2, 3);
			addExit(5, 4);
			addExit(1, 5);
			setPosition(1, 282, 363);
			setPosition(2, 283, 135);
			setPosition(4, 104, 181);
			setPosition(5, 152, 360);
			break;
		case CV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(1, 3);
			addExit(2, 2);
			addExit(5, 4);
			addExit(4, 1, 5);
			setPosition(1, 83, 246);
			setPosition(2, 149, 133);
			setPosition(4, 285, 243);
			setPosition(5, 153, 359);
			break;
		case DV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(4, 1, 2);
			addExit(2, 3);
			addExit(1, 4);
			addExit(5, 5);
			setPosition(1, 152, 364);
			setPosition(2, 86, 253);
			setPosition(4, 252, 185);
			setPosition(5, 281, 361);
			break;
		case EV:
			addClearing(1,2,4,5);
			addPath(1, 4);
			addPath(2, 5);
			addExit(1, 1);
			addExit(2, 2);
			addExit(4, 3, 4);
			addExit(5, 5);
			setPosition(1, 281, 130);
			setPosition(2, 151, 132);
			setPosition(4, 102, 314);
			setPosition(5, 280, 359);
			break;
		case LW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(2, 1, 5);
			addExit(4, 2);
			addExit(5, 3, 4);
			setPosition(2, 277, 247);
			setPosition(4, 153, 131);
			setPosition(5, 125, 297);
			break;
		case MW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(5, 1, 2);
			addExit(2, 3, 4);
			addExit(4, 5);
			setPosition(2, 115, 315);
			setPosition(4, 275, 366);
			setPosition(5, 217, 139);
			break;
		case NW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(5, 1, 5);
			addExit(2, 2, 3);
			addExit(4, 4);
			setPosition(2, 109, 187);
			setPosition(4, 137, 351);
			setPosition(5, 284, 244);
			break;
		case OW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(4, 1);
			addExit(5, 2, 3);
			addExit(2, 4, 5);
			setPosition(2, 208, 359);
			setPosition(4, 283, 134);
			setPosition(5, 117, 191);
			break;
		case PW:
			addClearing(2, 4, 5);
			addPath(2, 4);
			addExit(2, 1, 2);
			addExit(4, 3);
			addExit(5, 4, 5);
			setPosition(2, 192, 117);
			setPosition(4, 86, 230);
			setPosition(5, 213, 363);
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
			setPosition(1, 77, 170);
			setPosition(2, 204, 386);
			setPosition(3, 93, 316);
			setPosition(4, 363, 170);
			setPosition(5, 271, 187);
			setPosition(6, 183, 224);
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
			setPosition(1, 145, 368);
			setPosition(2, 79, 245);
			setPosition(3, 180, 242);
			setPosition(4, 360, 252);
			setPosition(5, 151, 134);
			setPosition(6, 270, 296);
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
			setPosition(1, 286, 361);
			setPosition(2, 282, 135);
			setPosition(3, 220, 228);
			setPosition(3, 220, 228);
			setPosition(4, 350, 275);
			setPosition(5, 157, 134);
			break;
		case HP:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(5, 1);
			addPath(1, 4);
			addPath(4, 2);
			addPath(3, 6);
			addExit(5, 0);
			addExit(6, 2);
			addExit(2, 3);
			addExit(3, 4);
			setPosition(1, 256, 336);
			setPosition(2, 87, 244);
			setPosition(3, 153, 350);
			setPosition(4, 233, 217);
			setPosition(5, 348, 250);
			setPosition(6, 150, 133);
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
			setPosition(1, 151, 157);
			setPosition(2, 90, 323);
			setPosition(3, 288, 370);
			setPosition(4, 262, 234);
			setPosition(5, 331, 147);
			setPosition(6, 184, 328);
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
			setPosition(1, 152, 128);
			setPosition(2, 287, 131);
			setPosition(3, 220, 244);
			setPosition(4, 150, 360);
			setPosition(5, 283, 363);
			setPosition(6, 87, 249);
			break;
		case CG:
			addClearing(1, 2, 3, 4, 5, 6);
			addPath(2, 5);
			addPath(5, 3);
			addPath(3, 6);
			addPath(4, 1);
			addSecretPath(2, 3);
			addSecretPath(1, 6);
			addExit(2, 3);
			setPosition(1, 367, 227);
			setPosition(2, 84, 248);
			setPosition(3, 174, 171);
			setPosition(4, 288, 320);
			setPosition(5, 282, 164);
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
			setPosition(1, 111, 159);
			setPosition(2, 217, 378);
			setPosition(3, 351, 300);
			setPosition(4, 220, 88);
			setPosition(5, 323, 148);
			setPosition(6, 243, 242);
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
			setPosition(1, 314, 268);
			setPosition(2, 159, 363);
			setPosition(3, 273, 376);
			setPosition(4, 209, 248);
			setPosition(5, 127, 157);
			setPosition(6, 353, 174);
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
			setPosition(1, 178, 169);
			setPosition(2, 291, 122);
			setPosition(3, 288, 239);
			setPosition(4, 70, 249);
			setPosition(5, 288, 372);
			setPosition(6, 163, 343);
			break;
		default:
			throw new RuntimeException();
		}
	}

	/**
	 * Add coordinates for clearings on the image
	 * @param clearingNumber Clearing number
	 * @param y Y position
	 * @param x X position
	 */
	private void setPosition(int clearingNumber, int y, int x) {
		TileClearing clearing = getClearing(clearingNumber);
		clearing.setYPosition(y);
		clearing.setXPosition(x);
		
	}

	/**
	 * Mark a clearing that has a path leading to one of the edges
	 * @param clearingNumber
	 * @param connectsToSide a list of tile sides that the clearing connects to
	 */
	protected void addExit(int clearingNumber, int ... connectsToSide) {
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
	protected void addPath(int clearing1, int clearing2) {
		clearings.get(clearing1).addPath(clearings.get(clearing2));
		clearings.get(clearing2).addPath(clearings.get(clearing1));
	}
	
	/**
	 * Add a secret path between two clearings
	 * @param clearing1
	 * @param clearing2
	 */
	protected void addSecretPath(int clearing1, int clearing2) {
		clearings.get(clearing1).addSecretPath(clearings.get(clearing2));
		clearings.get(clearing2).addSecretPath(clearings.get(clearing1));
	}

	protected void addClearing(int ... i) {
		for (int j : i) {
			clearings.put(j, new TileClearing(j));
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
	
	/**
	 * Get the clearing that exits at this side, affected by rotation.
	 * @param side
	 */
	public TileClearing getClearingOnSide(int side) {
		return tileExits.get(normalToRotated(side));
	}

	/**
	 * Takes the side of a rotated tile and gets the unrotated side
	 * @param side Inside number
	 * @return Outside number
	 */
	public int rotatedToNormal(int side) {
		return (6 + side + rotation) % 6;
	}
	
	/**
	 * 
	 * @param side Outside number
	 * @return Inside number
	 */
	public int normalToRotated(int side) {
		return (6 + side - rotation) % 6;
	}
	

	@Override
	public String toString() {
		return tileType.toString();
	}
	
	public static String[] getTileNames() {
		TileType[] tiles = TileType.values();
		ArrayList<String> s = new ArrayList<String>();
		for(TileType t: tiles) {
			s.add(t.toString());
		}
		return  s.toArray(new String[0]);
	}

	public Collection<TileClearing> getClearings() {
		return clearings.values();
	}

	public Map<Integer, TileClearing> getTileExits() {
		return tileExits;
	}
}
