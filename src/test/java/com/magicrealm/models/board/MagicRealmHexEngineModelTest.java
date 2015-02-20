package com.magicrealm.models.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;

public class MagicRealmHexEngineModelTest {
	
	
	@Test
	public void testGetLocationOfTile() {
		
		MagicRealmHexEngineModel model = new MagicRealmHexEngineModel(5, 5);
		
		GameTile borderland = new GameTile(TileType.B, 0);
		model.setValueAt(2, 3, borderland);
		HexPosition location = model.getLocation(borderland);
		assertEquals(2, location.getColumn());
		assertEquals(3, location.getRow());
		
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
}
