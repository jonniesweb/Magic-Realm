package com.magicrealm.models;

import java.io.Serializable;

public abstract class Belonging implements Serializable, Placeable {
	
	protected boolean active;
	
	public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	/*
	 * default image hack
	 */
	@Override
	public String getImageName() {
		return "";
	}

}
