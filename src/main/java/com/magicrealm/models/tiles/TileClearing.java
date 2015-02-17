package com.magicrealm.models.tiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Set<TileClearing> paths = new HashSet<TileClearing>();
	
	/**
	 * List of other clearings connected to via secret paths
	 */
	private Set<TileClearing> secretPaths = new HashSet<TileClearing>();;
	
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
