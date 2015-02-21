package com.magicrealm.models.tiles;

import static org.junit.Assert.*;

import org.junit.Test;

import com.magicrealm.models.tiles.GameTile.TileType;

public class GameTileTest {
	
	@Test
	public void testGetClearingOnSide() {
		
		GameTile tile = new GameTile(TileType.CN, 2);
		
		assertEquals(tile.getClearing(1), tile.getClearingOnSide(0));
		assertEquals(null, tile.getClearingOnSide(1));
		assertEquals(null, tile.getClearingOnSide(2));
		assertEquals(null, tile.getClearingOnSide(3));
		assertEquals(tile.getClearing(5), tile.getClearingOnSide(4));
		assertEquals(tile.getClearing(2), tile.getClearingOnSide(5));
		
		TestGameTile rotated0 = new TestGameTile(0);
		assertEquals(rotated0.getClearing(1), rotated0.getClearingOnSide(0));
		assertEquals(rotated0.getClearing(2), rotated0.getClearingOnSide(1));
		assertEquals(rotated0.getClearing(3), rotated0.getClearingOnSide(2));
		assertEquals(rotated0.getClearing(4), rotated0.getClearingOnSide(3));
		assertEquals(rotated0.getClearing(5), rotated0.getClearingOnSide(4));
		assertEquals(rotated0.getClearing(6), rotated0.getClearingOnSide(5));
		
		TestGameTile rotated2 = new TestGameTile(2);
		assertEquals(rotated2.getClearing(1), rotated2.getClearingOnSide(2));
		assertEquals(rotated2.getClearing(2), rotated2.getClearingOnSide(3));
		assertEquals(rotated2.getClearing(3), rotated2.getClearingOnSide(4));
		assertEquals(rotated2.getClearing(4), rotated2.getClearingOnSide(5));
		assertEquals(rotated2.getClearing(5), rotated2.getClearingOnSide(0));
		assertEquals(rotated2.getClearing(6), rotated2.getClearingOnSide(1));
		
		TestGameTile rotated5 = new TestGameTile(5);
		assertEquals(rotated5.getClearing(1), rotated5.getClearingOnSide(5));
		assertEquals(rotated5.getClearing(2), rotated5.getClearingOnSide(0));
		assertEquals(rotated5.getClearing(3), rotated5.getClearingOnSide(1));
		assertEquals(rotated5.getClearing(4), rotated5.getClearingOnSide(2));
		assertEquals(rotated5.getClearing(5), rotated5.getClearingOnSide(3));
		assertEquals(rotated5.getClearing(6), rotated5.getClearingOnSide(4));
	}
	
	/**
	 * A game tile with each of the 6 clearings mapped to an exit (side)
	 */
	private class TestGameTile extends GameTile {
		public TestGameTile(int rotation) {
			super(null, rotation);
		}
		
		@Override
		protected void initClearings(TileType type) {
			addClearing(1, 2, 3, 4, 5, 6);
			addExit(1, 0);
			addExit(2, 1);
			addExit(3, 2);
			addExit(4, 3);
			addExit(5, 4);
			addExit(6, 5);
		}
	}
	
	@Test
	public void testNormalToRotated() {
		TestGameTile tile = new TestGameTile(0);
		assertEquals(0, tile.rotatedToNormal(0));
		assertEquals(5, tile.rotatedToNormal(5));
		
		tile = new TestGameTile(1);
		assertEquals(1, tile.rotatedToNormal(0));
		assertEquals(0, tile.rotatedToNormal(5));
		
		tile = new TestGameTile(2);
		assertEquals(2, tile.rotatedToNormal(0));
		assertEquals(1, tile.rotatedToNormal(5));
	}
}
