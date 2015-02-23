package com.magicrealm.models.chits;

import com.magicrealm.models.Discoverable;

public abstract class ClearingMapChit extends MapChit implements Discoverable {
	
	public ClearingMapChit(String type) {
		super(type);
	}

	public abstract int getClearing();
	
}