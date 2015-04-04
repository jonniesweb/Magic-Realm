package com.magicrealm.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile;

public class NewBoardComponent extends JComponent {
	
	private final Log log = LogFactory.getLog(NewBoardComponent.class);
	
	private HexEngine<Graphics2D> engine;
	private List<HexTileClickListener> clickEvents = new ArrayList<>();
	
	public NewBoardComponent(MagicRealmHexEngineModel model) {
		
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
			
			// get the hex tile position of the tile that was clicked on
			final HexPosition position = engine.pointToHex(e.getX(), e.getY());
			if (engine.getModel().isPositionValid(position)) {
				
				// get the hex tile from the hex position
				final MagicRealmHexEngineModel model = (MagicRealmHexEngineModel) engine.getModel();
				GameTile tile = model.getValueAt(position);
				if (tile == null) {
					return;	
				}
				
				executeClickEvents(e, position, tile, engine);
			}
		}
	};
	
	public void addHexTileClickEvent(HexTileClickListener clickEvent) {
		clickEvents.add(clickEvent);
	}
	
	public void removeHexTileClickEvent(HexTileClickListener clickEvent) {
		clickEvents.remove(clickEvent);
	}
	
	/**
	 * Execute all {@link HexTileClickListener}s that have been added to this class.
	 * 
	 * @param e
	 * @param position
	 * @param tile
	 * @param engine
	 */
	private void executeClickEvents(MouseEvent e, HexPosition position, GameTile tile,
			HexEngine<?> engine) {
		for (HexTileClickListener clickEvent : clickEvents) {
			clickEvent.hexTileClicked(e, position, tile, engine);
		}
	}
}
