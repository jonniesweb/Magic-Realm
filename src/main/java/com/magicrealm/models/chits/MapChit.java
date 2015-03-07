package com.magicrealm.models.chits;

import java.io.Serializable;

public abstract class MapChit implements Serializable {
	
	private String type;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param type
	 */
	public MapChit(String type) {
		this.type = type;
	}
	
	
	
}
