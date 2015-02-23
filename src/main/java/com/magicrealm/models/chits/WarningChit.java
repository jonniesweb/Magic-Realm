package com.magicrealm.models.chits;


public class WarningChit extends MapChit {
	
	private enum tile {valley, woods, caves, mountains}
	public enum warning {smoke, ruins, dank, stink, bones}

	private warning warningTile;
	
	public WarningChit(warning warn) {
		super(warn.toString());
		this.setWarningTile(warn);
	}

	public warning getWarningTile() {
		return warningTile;
	}

	public void setWarningTile(warning warningTile) {
		this.warningTile = warningTile;
	}
	
	@Override
	public String toString() {
		return "WarningTile: " + warningTile;
	}
}
