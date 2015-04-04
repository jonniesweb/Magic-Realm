package com.magicrealm.gui;

import java.awt.event.MouseEvent;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.utils.GameLog;

/**
 * Log the tile's warning, site and sound chits to the {@link GameLog} whenever
 * this listener is activated.
 */
public class LogHexTileInformation implements HexTileClickListener {
	
	@Override
	public void hexTileClicked(MouseEvent e, HexPosition position, GameTile tile,
			HexEngine<?> engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("Tile: ");
		sb.append(tile.getTileType());
		sb.append(" contains");
		if (tile.getWarningChit() != null) {
			sb.append(" the warning chit ");
			sb.append(tile.getWarningChit());
		}
		if (tile.getSiteSoundChit() != null) {
			sb.append(" the site/sound chit ");
			sb.append(tile.getSiteSoundChit());
		}
		
		GameLog.log(sb.toString());
		
	}
	
}
