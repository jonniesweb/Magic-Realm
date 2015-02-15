package com.magicrealm.gui;

import java.util.ArrayList;
import java.util.List;

public class TileClearing {
	
	/**
	 * The number that this clearing represents on the game tile
	 */
	private int clearingNumber;
	
	/**
	 * Stack of tiles that this clearing contains
	 */
	private List tiles;
	
	/**
	 * List of other clearings connected to via normal paths
	 */
	private List<TileClearing> paths = new ArrayList<TileClearing>();
	
	/**
	 * List of other clearings connected to via secret paths
	 */
	private List<TileClearing> secretPaths = new ArrayList<TileClearing>();;
	
	public void addPath(TileClearing tileClearing) {
		if (tileClearing == this)
			throw new RuntimeException("Path cannot lead to itself!");
		paths.add(tileClearing);
	}
	
	public void addSecretPath(TileClearing tileClearing) {
		if (tileClearing == this)
			throw new RuntimeException("Secret path cannot lead to itself!");
		secretPaths.add(tileClearing);
	}
	
	
}
