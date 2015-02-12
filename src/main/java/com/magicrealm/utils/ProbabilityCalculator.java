package com.magicrealm.utils;

import java.util.Random;

public class ProbabilityCalculator {
	
	private static final int TOTAL_COMBINATIONS = 36;
	private static final int HIDE = 25;
	private static final int DISCOVER_TREASURE = 8;
	private static final int DISCOVER_PASSAGE = 9;
	
	public static boolean calculateHide() {
		return getRandom() < HIDE;
	}
	
	public static boolean findTreasure() {
		return getRandom() < DISCOVER_TREASURE;
	}
	
	public static boolean findPassage() {
		return getRandom() < DISCOVER_PASSAGE;
	}
	
	private static int getRandom() {
		Random rand = new Random();
		return rand.nextInt(TOTAL_COMBINATIONS);
	}

}
