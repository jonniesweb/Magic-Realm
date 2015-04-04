package com.magicrealm.gui;

import java.awt.event.MouseEvent;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.tiles.GameTile;

/**
 * A listener that is executed whenever a {@link GameTile} is clicked. Must be
 * added to an object that accepts {@link HexTileClickListener}. The
 * {@link #hexTileClicked(MouseEvent, HexPosition, GameTile, HexEngine)} method
 * is executed whenever the object that accepted the
 * {@link HexTileClickListener} deems appropriate.
 */
public abstract interface HexTileClickListener {
	
	/**
	 * Called whenever a hex tile is clicked
	 * @param e The mouse event of the click
	 * @param position position of the hex tile
	 * @param tile The specific tile that was clicked on
	 * @param engine the engine used by the game board
	 */
	void hexTileClicked(MouseEvent e, HexPosition position, GameTile tile,
			HexEngine<?> engine);
}
