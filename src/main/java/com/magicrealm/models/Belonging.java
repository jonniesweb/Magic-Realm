package com.magicrealm.models;

public abstract class Belonging {
	
	protected boolean active;
	
	public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}

}
