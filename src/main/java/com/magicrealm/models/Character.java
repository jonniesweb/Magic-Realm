package com.magicrealm.models;

import java.awt.Image;

public class Character {
	
	private String name;
	private Image picture;
	private String description;
	private enum vulnerability {light, medium, heavy};
	private boolean attentionChit;
	private int fame;
	private int notoriety;
	private CombatChit combatChit;
	private int gold;
	private Dwelling startingLocation;
	
//	private Person tradingRelationships;
//	private int discoveries;

}
