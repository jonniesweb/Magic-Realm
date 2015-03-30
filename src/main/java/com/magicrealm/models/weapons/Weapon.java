package com.magicrealm.models.weapons;

import com.magicrealm.models.Belonging;
import com.magicrealm.models.Weight;

public abstract class Weapon extends Belonging {

	public enum Attack {
		STRIKING, MISSLE
	}

	protected String name;
	protected Weight weight;
	protected Weight harm;
	protected Attack attack;
	protected int sharpness; // number of stars
	protected int length;
	protected int speed; // the integer on the weapon, if 0 the speed is the
							// chit speed
	protected boolean alerted;
	protected int price;

	protected Weapon(String name, Attack attack, int length, int price,
			Weight weight) {
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

	public void setAttack(Attack a) {
		attack = a;
	}

	public void setPrice(int p) {
		price = p;
	}

	public void setWeight(Weight w) {
		weight = w;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public void setSharpness(int s) {
		sharpness = s;
	}

	public Weight getInflictedHarm() {
		return this.harm.increment(sharpness);
	}
	
	public Weight getInflictedHarmThroughArmor() {
		int newSharpness = sharpness - 1;
		if(sharpness > 0)
			return this.harm.increment(newSharpness);
		else if(sharpness < 0)
			return this.harm.decrement(newSharpness);
		return this.harm;
	}

	public String getName() {
		return name;
	}

	public Weight getWeight() {
		return weight;
	}

	public Weight getHarm() {
		return harm;
	}

	public Attack getAttack() {
		return attack;
	}

	public int getSharpness() {
		return sharpness;
	}

	public int getLength() {
		return length;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPrice() {
		return price;
	}

	public abstract void setAlertStats();

	public abstract void setSleepStats();

}
