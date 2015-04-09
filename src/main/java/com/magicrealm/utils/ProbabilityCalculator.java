package com.magicrealm.utils;

import java.util.Random;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public abstract class ProbabilityCalculator {
	
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
	
	public static boolean calculateHide(String clientId) {
		return getResult(clientId, "Select hide roll") != Result.SIX;
	}
	
	public static Result getResult(MRCharacter character) {
		return getResult(character, "Select a Result");
	}
	
	public static Result getResult(MRCharacter character, String message){
		if(character != null && character.isCheatModeEnabled()) {
			Result[] choices = Result.values();
			IClientService service = ServerGameState.getInstance().getClientService(character.getCharacterType());
			return (Result) service.clientSelect(choices, message);
		}
		int num;
		num = getRandom();
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
	
	public static Result getResult(String clientId) {
		return getResult(ServerGameState.getInstance().getCharacter(clientId));
	}
	
	public static Result getResult(String clientId, String message) {
		return getResult(ServerGameState.getInstance().getCharacter(clientId), message);
	}
	
	public static boolean findTreasure() {
		return getRandom() < DISCOVER_TREASURE;
	}
	
	public static boolean findPassage() {
		return getRandom() < DISCOVER_PASSAGE;
	}
	
	public static Result[] getResultChoices() {
		return new Result[] {Result.TWO, Result.THREE, Result.FOUR, Result.FIVE};
	}
	
	private static int getRandom() {
		Random rand = new Random();
		return rand.nextInt(TOTAL_COMBINATIONS);
	}


}
