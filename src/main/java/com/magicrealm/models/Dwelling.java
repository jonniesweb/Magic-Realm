package com.magicrealm.models;

import java.awt.Image;
import java.io.Serializable;

import com.magicrealm.utils.ImageCache;


public class Dwelling implements Serializable, Placeable {

	public static final String SMALL_FIRE = "small_fire";
	public static final String LARGE_FIRE = "large_fire";
	public static final String INN = "inn";
	public static final String HUT = "hut";
	public static final String HOUSE = "house";
	public static final String GUARD = "guard";
	public static final String CHAPEL = "chapel";

	public enum dwelling { inn, house, guard, chapel }
	
	private dwelling dwellingType;
	
	public Dwelling(dwelling dwellingType) {
		this.dwellingType = dwellingType;
	}

	@Override
	public String getImageName() {
		return getImageString(dwellingType);
	}

	public dwelling getDwellingType() {
		return dwellingType;
	}
	
	private static String getImageString(dwelling type) {
		switch (type) {
		case inn:
			return INN;
		case chapel:
			return CHAPEL;
		case guard:
			return GUARD;
		case house:
			return HOUSE;
		default:
			throw new RuntimeException("Invalid dwelling type");
		}
	}

}
