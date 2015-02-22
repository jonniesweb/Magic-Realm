package com.magicrealm.models.chits;


public class WarningChit extends MapChit {
	
	private enum tile {valley, woods, caves, mountains}
	public enum warning {smoke, ruins, dank, stink, bones}

	private warning warningTile;
	
	public WarningChit(warning warning) {
		this.setWarningTile(warning);
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
