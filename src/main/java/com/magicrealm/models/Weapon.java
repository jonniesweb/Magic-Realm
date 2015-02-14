package com.magicrealm.models;

public class Weapon extends Belonging {
	
	private enum attack {striking, missle};
	private Weight weight;
	private int sharpness;
	private int length;
	private boolean alerted;
	private int price;
	
	public void alert() {
		this.alerted = true;
	}
	
	public void sleep() {
		this.alerted = false;
	}

}
