package com.magicrealm.gui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.renders.swing.ColorHexRender;


public final class HexImageRenderer extends ColorHexRender {
	
	private static final Log log = LogFactory.getLog(HexImageRenderer.class);
	
	
	@Override
	public void renderHexCell(HexEngine<Graphics2D> engine, Graphics2D g,
			float x, float y, int col, int row) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		
		GameTile tile = (GameTile) engine.getModel().getValueAt(col, row);
		// ignore all null spaces on the gameboard
		if (tile == null)
			return;
		
		String filename = getImage(tile);
		log.info("get image: " + filename);
		
		try {
			// draw image
			BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(filename + "1.gif"));
			g.drawImage(image, (int) x, (int) y, (int) engine.getCellWidth(), (int) engine.getCellHeight(), null);
		} catch (IOException e) {
			log.error("Unable to load image: " + filename, e);
		}
		
	}

	/**
	 * Get the filename of the tile according the the GameTile
	 * @param tile
	 * @return
	 */
	private String getImage(GameTile tile) {
		if (tile == null) {
			return ""; // XXX remove
		}
		switch (tile.getTileType()) {
		case AV:
			return "awfulvalley";
		case BV:
			return "badvalley";
		case CV:
			return "curstvalley";
		case DV:
			return "darkvalley";
		case EV:
			return "evilvalley";
		case LW:
			return "lindenwoods";
		case MW:
			return "maplewoods";
		case NW:
			return "nutwoods";
		case OW:
			return "oakwoods";
		case PW:
			return "pinewoods";
		case B:
			return "borderland";
		case CN:
			return "cavern";
		case CS:
			return "caves";
		case HP:
			return "highpass";
		case R:
			return "ruins";
		case CF:
			return "cliff";
		case CG:
			return "crag";
		case DW:
			return "deepwoods";
		case L:
			return "ledges";
		case M:
			return "mountain";
		default:
			throw new RuntimeException();
		}
	}
	
	
	
}