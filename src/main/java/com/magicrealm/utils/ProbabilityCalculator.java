package com.magicrealm.utils;

import java.util.Random;

public abstract class ProbabilityCalculator {
	
	public enum Peer {CHOICE, CLUES_PATHS, ENEMIES_PATHS, ENEMIES, CLUES, NOTHING};
	public enum Locate {CHOICE, PASSAGES_CLUES, PASSAGES, DISCOVER, NOTHING};
	public enum Result {ONE, TWO, THREE, FOUR, FIVE, SIX};
	private static final int TOTAL_COMBINATIONS = 36;
	private static final int HIDE = 25;
	private static final int DISCOVER_TREASURE = 8;
	private static final int DISCOVER_PASSAGE = 9;
	public static final int ONE_PROBABILITY = 0;
	public static final int TWO_PROBABILITY = 3;
	public static final int THREE_PROBABILITY = 8;
	public static final int FOUR_PROBABILITY = 15;
	public static final int FIVE_PROBABILITY = 24;
	
	public static boolean calculateHide() {
		return getRandom() < HIDE;
	}
	
	public static Peer getPeerResult() {
		int num = getRandom();
		if(num == ONE_PROBABILITY) {
			return Peer.CHOICE;
		} else if(num <= TWO_PROBABILITY) {
			return Peer.CLUES_PATHS;
		} else if(num <= THREE_PROBABILITY) {
			return Peer.ENEMIES_PATHS;
		} else if(num <= FOUR_PROBABILITY) {
			return Peer.ENEMIES;
		} else if(num <= FIVE_PROBABILITY) {
			return Peer.CLUES;
		} else {
			return Peer.NOTHING;
		}
	}
	
	public static Locate getLocateResult() {
		int num = getRandom();
		if(num == ONE_PROBABILITY) {
			return Locate.CHOICE;
		} else if(num <= TWO_PROBABILITY) {
			return Locate.PASSAGES_CLUES;
		} else if(num <= THREE_PROBABILITY) {
			return Locate.PASSAGES;
		} else if(num <= FOUR_PROBABILITY) {
			return Locate.DISCOVER;
		} else {
			return Locate.NOTHING;
		}
	}
	
	public static Result getResult() {
		int num = getRandom();
		if(num == ONE_PROBABILITY) {
			return Result.ONE;
		} else if(num <= TWO_PROBABILITY) {
			return Result.TWO;
		} else if(num <= THREE_PROBABILITY) {
			return Result.THREE;
		} else if(num <= FOUR_PROBABILITY) {
			return Result.FOUR;
		} else if(num <= FIVE_PROBABILITY) {
			return Result.FIVE;
		} else {
			return Result.SIX;
		}
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
