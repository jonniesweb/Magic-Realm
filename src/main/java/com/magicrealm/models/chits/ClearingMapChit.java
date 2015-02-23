package com.magicrealm.models.chits;

public abstract class ClearingMapChit extends MapChit {
	
	public ClearingMapChit(String type) {
		super(type);
	}

	public abstract int getClearing();
	
}