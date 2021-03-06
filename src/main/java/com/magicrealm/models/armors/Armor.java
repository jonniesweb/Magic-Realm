package com.magicrealm.models.armors;

import com.magicrealm.models.Belonging;
import com.magicrealm.models.Weight;

public abstract class Armor extends Belonging {
	
	public enum Protection {SWING, THRUST, SMASH, ONE};
	public enum State {INTACT, DAMAGED, DESTROYED};
	public enum Slot {SUIT, BREASTPLATE, HELMET, SHIELD}
	protected int price;
	protected Protection[] protection;
	protected State state;
	protected Slot slot;
	protected Weight weight;
	protected String name;
	
	protected Armor(String name, Slot slot, Weight weight, Protection[] protection) {
		this.slot = slot;
		this.name = name;
		this.weight = weight;
		this.protection = protection;
		this.intact();
	}
	
	public void intact() {
		state = State.INTACT;
		setIntactPrice();
	}
	
	public void damaged() {
		state = State.DAMAGED;
		setDamagedPrice();
	}
	
	public void destroyed() {
		state = State.DESTROYED;
		setDestroyedPrice();
	}

	public int getPrice() {
		return price;
	}

	public Protection[] getProtection() {
		return protection;
	}

	public State getState() {
		return state;
	}

	public Slot getSlot() {
		return slot;
	}

	public Weight getWeight() {
		return weight;
	}

	public String getName() {
		return name;
	}

	public abstract void setIntactPrice();
	
	public abstract void setDamagedPrice();
	
	public abstract void setDestroyedPrice();

}
