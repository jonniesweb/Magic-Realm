package com.magicrealm.models.chits;

public abstract class MapChit {
	
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
