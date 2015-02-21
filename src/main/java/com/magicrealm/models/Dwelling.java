package com.magicrealm.models;

import java.awt.Image;

import com.magicrealm.utils.ImageCache;


public class Dwelling implements Placeable {

	public static final String SMALL_FIRE = "small_fire";
	public static final String LARGE_FIRE = "large_fire";
	public static final String INN = "inn";
	public static final String HUT = "hut";
	public static final String HOUSE = "house";
	public static final String GUARD = "guard";
	public static final String CHAPEL = "chapel";

	
	private String dwelling;
	
	public Dwelling(String dwelling) {
		this.dwelling = dwelling;
	}

	@Override
	public Image getImage() {
		return ImageCache.getImage(dwelling);
	}
	
	@Override
	public String getImageName() {
		return dwelling;
	}
}
