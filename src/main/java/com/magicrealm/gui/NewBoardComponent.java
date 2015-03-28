package com.magicrealm.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class NewBoardComponent extends JComponent {
	
	private final Log log = LogFactory.getLog(NewBoardComponent.class);
	
	private HexEngine<Graphics2D> engine;
	private UIMediator mediator;
	
	public NewBoardComponent(UIMediator mediator, MagicRealmHexEngineModel model) {
		this.mediator = mediator;
		
		engine = new HexEngine<Graphics2D>(200, 200, 0.25f, HexEngine.ORIENTATION_HORIZONTAL);
		engine.setModel(model);
		engine.setRenderer(new HexImageRenderer());
		
		addMouseListener(clearingFinder);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		engine.draw((Graphics2D) g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		final HexRect2D rect = engine.getVisibleSize();
		return new Dimension(rect.getWidthAsInt(), rect.getHeightAsInt());
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		engine.setModel(model);
		repaint();
		log.debug("Model updated, repainting the GameBoardComponent");
	}
	
	private MouseAdapter clearingFinder = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			TileClearing closest = null;
			
			// get the hex tile position of the tile that was clicked on
			final HexPosition position = engine.pointToHex(e.getX(), e.getY());
			if (engine.getModel().isPositionValid(position)) {
				
				// get the hex tile from the hex position
				final MagicRealmHexEngineModel model = (MagicRealmHexEngineModel) engine.getModel();
				GameTile tile = model.getValueAt(position);
				if (tile == null) {
					return;
				}
				
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
	};
}
