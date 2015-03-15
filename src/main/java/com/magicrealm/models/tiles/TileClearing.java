package com.magicrealm.models.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Discoverable;
import com.magicrealm.models.Placeable;

public class TileClearing implements Serializable, Discoverable {
	
	public enum ClearingType {
		MOUNTAIN, CAVE, WOODS
	}
	
	private ClearingType clearingType;
	
	/**
	 * The number that this clearing represents on the game tile
	 */
	private int clearingNumber;
	
	/**
	 * Stack of tiles that this clearing contains
	 */
	private List<Placeable> chits = new ArrayList<Placeable>();
	
	/**
	 * List of other clearings connected to via normal paths
	 */
	private Set<TileClearing> paths = new HashSet<TileClearing>();
	
	/**
	 * List of other clearings connected to via secret paths
	 */
	private TileClearing secretPath = null;

	private int ypos;

	private int xpos;;
	
	public TileClearing(int clearingNumber) {
		this(clearingNumber, ClearingType.WOODS);
	}
	
	public TileClearing(int clearingNumber, ClearingType clearingType) {
		this.setClearingNumber(clearingNumber);
		this.clearingType = clearingType;
	}

	public void addPath(TileClearing tileClearing) {
		if (tileClearing == this)
			throw new RuntimeException("Path cannot lead to itself!");
		paths.add(tileClearing);
	}
	
	public void addSecretPath(TileClearing tileClearing) {
		if (tileClearing == this)
			throw new RuntimeException("Secret path cannot lead to itself!");
		secretPath = tileClearing;
	}
	
	public void addChit(Placeable placeable) {
		chits.add(placeable);
	}
	
	public List<Placeable> getChits() {
		return chits;
	}

	public int getClearingNumber() {
		return clearingNumber;
	}

	public void setClearingNumber(int clearingNumber) {
		this.clearingNumber = clearingNumber;
	}
	
	public Set<TileClearing> getConnectedClearings() {
		return Collections.unmodifiableSet(paths);
	}
	
	public TileClearing getConnectedSecretClearing() {
		return secretPath;
	}
	
	public TileClearing[] getPlayerConnectedClearings(MRCharacter player) {
		Set<TileClearing> clearings = getConnectedClearings();
		if(player.getDiscoveries().contains(secretPath)) {
			clearings.add(secretPath);
		}
		return clearings.toArray(new TileClearing[0]);
	}

	public void setYPosition(int ypos) {
		this.ypos = ypos;
	}
	
	public int getYPosition() {
		return ypos;
	}
	
	public void setXPosition(int xpos) {
		this.xpos =xpos;
	}
	
	public int getXPosition() {
		return xpos;
	}

	public ClearingType getClearingType() {
		return clearingType;
	}
	
}
