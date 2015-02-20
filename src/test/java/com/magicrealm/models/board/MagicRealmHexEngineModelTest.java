package com.magicrealm.models.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;

public class MagicRealmHexEngineModelTest {
	
	
	@Test
	public void testGetLocationOfTile() {
		
		MagicRealmHexEngineModel model = new DefaultMagicRealmHexEngineModel(0, 0);
		
		HexPosition location = model.getLocation(model.getTile(TileType.B));
		assertEquals(4, location.getColumn());
		assertEquals(1, location.getRow());
		
		GameTile cn = new GameTile(TileType.CN, 0);
		model.setValueAt(0, 0, cn);
		location = model.getLocation(cn);
		assertEquals(0, location.getColumn());
		assertEquals(0, location.getRow());
		
		GameTile bv = new GameTile(TileType.BV, 0);
		model.setValueAt(4, 4, bv);
		location = model.getLocation(bv);
		assertEquals(4, location.getColumn());
		assertEquals(4, location.getRow());
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetOutOfBoundsLocation() {
		MagicRealmHexEngineModel model = new MagicRealmHexEngineModel(5, 5);
		model.setValueAt(5, 5, new GameTile(TileType.B, 0));
	}
	
	@Test
	public void testGetAdjacentTile() {
		DefaultMagicRealmHexEngineModel model = new DefaultMagicRealmHexEngineModel(0, 0);
		System.out.println("should be " + model.getLocation(model.getTile(TileType.MW)));
		
		GameTile badValley = model.getTile(TileType.BV);
		assertEquals(model.getTile(TileType.MW), model.getTileAdjacentToEdge(badValley, 0));
		assertEquals(model.getTile(TileType.CS), model.getTileAdjacentToEdge(badValley, 1));
		assertEquals(model.getTile(TileType.M), model.getTileAdjacentToEdge(badValley, 2));
		assertEquals(model.getTile(TileType.CN), model.getTileAdjacentToEdge(badValley, 3));
		assertEquals(model.getTile(TileType.B), model.getTileAdjacentToEdge(badValley, 4));
		assertEquals(model.getTile(TileType.OW), model.getTileAdjacentToEdge(badValley, 5));
		
		GameTile borderland = model.getTile(TileType.B);
		assertEquals(model.getTile(TileType.OW), model.getTileAdjacentToEdge(borderland, 0));
		assertEquals(model.getTile(TileType.BV), model.getTileAdjacentToEdge(borderland, 1));
		assertEquals(model.getTile(TileType.CN), model.getTileAdjacentToEdge(borderland, 2));
		assertEquals(model.getTile(TileType.HP), model.getTileAdjacentToEdge(borderland, 3));
		assertEquals(model.getTile(TileType.EV), model.getTileAdjacentToEdge(borderland, 4));
		assertEquals(model.getTile(TileType.L), model.getTileAdjacentToEdge(borderland, 5));
	}
}
