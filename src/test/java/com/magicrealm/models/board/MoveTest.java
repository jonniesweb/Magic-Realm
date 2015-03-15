package com.magicrealm.models.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.magicrealm.activity.Move;
import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.MRCharacter.character;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.exceptions.GameAlreadyStartedException;
import com.magicrealm.models.BirdsongActivities;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.networking.AbstractMRTest;
import com.magicrealm.utils.TileClearingLocation;

/**
 * Requirement:
 * <code>This board must correctly refresh upon the execution of each action of a
 * character</code> <br>
 * <br>
 * 
 */
public class MoveTest extends AbstractMRTest {
	
	/**
	 * Moves the character from one clearing to another, checking that the
	 * client updated its board
	 */
	@Test
	public void testCharacterMoveBoardUpdates() throws Exception {
		startGame();
		TileClearingLocation firstLocation = service.getGameBoard().getChitLocation(new Amazon());
		moveCharacterOnce(firstLocation);
		System.out.println(firstLocation);
		
		TileClearingLocation secondLocation = service.getGameBoard().getChitLocation(new Amazon());
		assertNotEquals(secondLocation, firstLocation);
	}

	private void moveCharacterOnce(TileClearingLocation currentLocation) {
		BirdsongActivities activities = new BirdsongActivities(currentLocation);
		activities.getQueuedActivities().add(new Move(new TileClearingLocation(TileType.B, 1)));
		service.setActivities(activities);
	}

	/**
	 * select character, starting location, then start the game.
	 */
	private void startGame() throws CharacterAlreadyTakenException,
			GameAlreadyStartedException {
		ClientGameState.getInstance().setCharacter(service.selectCharacter(character.amazon));
		service.setStartingLocationForCharacter(dwelling.inn);
		service.startGame();
	}
	
	/**
	 * Performs multiple actions, ensuring that the board is updated on the
	 * client between each action
	 */
	@Test
	public void testMultipleActions() throws Exception {
		
	}
}
