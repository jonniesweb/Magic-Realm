package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.magicrealm.models.Clearingable;

public class TileClearing {
	
	/**
	 * The number that this clearing represents on the game tile
	 */
	private int clearingNumber;
	
	/**
	 * Stack of tiles that this clearing contains
	 */
	private List<Clearingable> tiles = new ArrayList<Clearingable>();
	
	/**
	 * List of other clearings connected to via normal paths
	 */
	private Set<TileClearing> paths = new HashSet<TileClearing>();
	
	/**
	 * List of other clearings connected to via secret paths
	 */
	private Set<TileClearing> secretPaths = new HashSet<TileClearing>();;
	
	public TileClearing(int clearingNumber) {
		this.setClearingNumber(clearingNumber);
	}

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
	
	public void addToClearing(Clearingable clearingable) {
		tiles.add(clearingable);
	}

	public int getClearingNumber() {
		return clearingNumber;
	}

	public void setClearingNumber(int clearingNumber) {
		this.clearingNumber = clearingNumber;
	}
	
}
