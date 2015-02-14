package com.magicrealm.models;

public abstract class Belonging {
	
	protected boolean active;
	
	public void activate() {
		this.active = true;
	}
	
	public void inactivate() {
		this.active = false;
	}

}
