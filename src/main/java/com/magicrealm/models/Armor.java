package com.magicrealm.models;

public class Armor extends Belonging {
	
	private enum Protection {thrust, smash, all};
	private enum State {intact, damaged, destroyed};
	private enum Slot {suit, breastplate, helmet, shield}
	private int price;
	private Protection protection;
	private State state;
	private Slot slot;
	private Weight weight;

}
