package com.magicrealm.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

/**
 * Update the {@link UIMediator} with the chits from the closest clearing
 * clicked on in the {@link GameTile}.
 */
public class ClearingClickedListener implements HexTileClickListener {
	
	private final Log log = LogFactory.getLog(ClearingClickedListener.class);
	private UIMediator mediator;

	public ClearingClickedListener(UIMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void hexTileClicked(MouseEvent e, HexPosition position, GameTile tile, HexEngine engine) {
		TileClearing closest = null;
		// hex tile anchor point
		float hexx = engine.calculateX(position.getColumn(), position.getRow());
		float hexy = engine.calculateY(position.getColumn(), position.getRow());

		// positions relative to the hex tile anchor point
		float ihexx = e.getX() - hexx;
		float ihexy = e.getY() - hexy;
		
		double distance = 100000000000000d; // big number
		Point scaledClearingCoords = null;
		
		// iterate over clearings to find the one closest to the cursor
		for (TileClearing clearing : tile.getClearings()) {
			if (clearing == null) {
				continue;
			}
			Point rotatedPoint = HexImageRenderer.rotateCoordinates(clearing.getXPosition(), clearing.getYPosition(), tile.getRotation());
			scaledClearingCoords = HexImageRenderer.scaleCoordinates(engine, rotatedPoint);
			
			log.trace("point coords " + clearing.getClearingNumber() + " "+ scaledClearingCoords);
			double distanceToClearing = scaledClearingCoords.distance(ihexx, ihexy);
			if (distanceToClearing < distance) {
				log.trace("found closer " + distanceToClearing);
				closest = clearing;
				distance = distanceToClearing;
			}
		}
		
		// update the mediator with the location and the clearing that was clicked
		TileClearingLocation location = new TileClearingLocation(tile.getTileType(), closest.getClearingNumber());
		mediator.clearingSelected(location, closest);
		
		log.trace("tile "
				+ tile.getTileType()
				+ " x "
				+ e.getX()
				+ " y "
				+ e.getY()
				+ " hx "
				+ hexx
				+ " hy "
				+ hexy
				+ " ihx "
				+ ihexx
				+ " ihy "
				+ ihexy);
		log.info("clicked on clearing number "
				+ closest.getClearingNumber()
				+ " distance away "
				+ scaledClearingCoords);
		
		if (closest != null) {
			for (Placeable placeable : closest.getChits()) {
				log.debug("chit in clearing: " + placeable);
			}
		}
	}
}
