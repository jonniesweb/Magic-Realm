package com.magicrealm.models;

public abstract class Weapon extends Belonging {
	
	public enum Attack {STRIKING, MISSLE}
	protected String name;
	protected Weight weight;
	protected Weight harm;
	protected Attack attack;
	protected int sharpness; //number of stars
	protected int length;
	protected int speed; //the integer on the weapon, if 0 the speed is the chit speed
	protected boolean alerted;
	protected int price;
	
	protected Weapon(String name, Attack attack, int length, int price, Weight weight) {
		this.attack = attack;
		this.price = price;
		this.weight = weight;
		this.harm = weight;
		this.length = length;
		this.name = name;
		this.sharpness = 0;
		this.speed = 0;
		sleep();
	}
	
	public void alert() {
		setAlertStats();
		this.alerted = true;
	}
	
	public void sleep() {
		setSleepStats();
		this.alerted = false;
	}
	
	public abstract void setAlertStats();
	
	public abstract void setSleepStats();

}
