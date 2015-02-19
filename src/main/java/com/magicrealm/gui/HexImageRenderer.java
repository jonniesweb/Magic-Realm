package com.magicrealm.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.renders.swing.ColorHexRender;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.utils.ImageCache;


public final class HexImageRenderer extends ColorHexRender {
	
	private static final Log log = LogFactory.getLog(HexImageRenderer.class);
	
	
	@Override
	public void renderHexCell(HexEngine<Graphics2D> engine, Graphics2D graphic,
			float x, float y, int col, int row) {
		
		GameTile tile = (GameTile) engine.getModel().getValueAt(col, row);
		// ignore all empty spaces on the gameboard
		if (tile == null)
			return;
		
		String filename = getImage(tile);
		log.trace("drawing tile " + filename);
		
		// get tile from cache and rotate
		BufferedImage image = rotateImage(tile, ImageCache.getImage(filename));
		
		// draw the tile onto the gameboard
		graphic.drawImage(image, (int) x, (int) y, (int) engine.getCellWidth(), (int) engine.getCellHeight(), null);
		
		// draw chits
		if (tile.getTileType() == TileType.B) {
		} else if (tile.getTileType() == TileType.DV) {
			// show guard on 5
			drawChitImage(graphic, x, y, getX(engine, image, 140f), getY(engine, image, 140f), "guard");
		} else if (tile.getTileType() == TileType.CV) {
			// show house on 5
			drawChitImage(graphic, x, y, getX(engine, image, 360f), getY(engine, image, 281f), "house");
		} else if (tile.getTileType() == TileType.BV) {
			// show inn on 5
			drawChitImage(graphic, x, y, getX(engine, image, 360f), getY(engine, image, 156f), "inn");
		} else if (tile.getTileType() == TileType.AV) {
			// show chapel on 5
			drawChitImage(graphic, x, y, getX(engine, image, 360f), getY(engine, image, 123f), "chapel");
		} else if (tile.getTileType() == TileType.EV) {
			// show 2 ghosts on 5
			drawChitImage(graphic, x, y, getX(engine, image, 143f), getY(engine, image, 143f), "ghost");
			drawChitImage(graphic, x, y, getX(engine, image, 133f), getY(engine, image, 133f), "ghost");
		}
		
	}

	private void drawChitImage(Graphics2D graphic, float x, float y,
			float imgX, float imgY, String imageName) {
		Image innImage = ImageCache.getImage(imageName);
		graphic.drawImage(innImage, (int) (x + imgX - 25),
				(int) (y + imgY - 25), 50, 50, null);
	}

	private float getY(HexEngine<Graphics2D> engine, BufferedImage image,
			float yPos) {
		float by = yPos / image.getHeight();
		float zy = by * engine.getCellHeight();
		return zy;
	}

	private float getX(HexEngine<Graphics2D> engine, BufferedImage image,
			float xPos) {
		float bx = xPos / image.getWidth();
		float zx = bx * engine.getCellWidth();
		return zx;
	}

	private BufferedImage rotateImage(GameTile tile, BufferedImage image) {
		// create a blank image for the rotation
		BufferedImage rotatedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D rotatedImageGraphics = (Graphics2D) rotatedImage
				.getGraphics();
		double imageX = image.getWidth() / 2.0D;
		double imageY = image.getHeight() / 2.0D;
		int degrees = 60 * tile.getRotation();
		AffineTransform transform = AffineTransform.getRotateInstance(
				Math.toRadians(degrees), imageX, imageY);

		// draw the image, applying the rotation
		rotatedImageGraphics.drawImage(image, transform, null);
		return rotatedImage;
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
			return "awfulvalley1";
		case BV:
			return "badvalley1";
		case CV:
			return "curstvalley1";
		case DV:
			return "darkvalley1";
		case EV:
			return "evilvalley1";
		case LW:
			return "lindenwoods1";
		case MW:
			return "maplewoods1";
		case NW:
			return "nutwoods1";
		case OW:
			return "oakwoods1";
		case PW:
			return "pinewoods1";
		case B:
			return "borderland1";
		case CN:
			return "cavern1";
		case CS:
			return "caves1";
		case HP:
			return "highpass1";
		case R:
			return "ruins1";
		case CF:
			return "cliff1";
		case CG:
			return "crag1";
		case DW:
			return "deepwoods1";
		case L:
			return "ledges1";
		case M:
			return "mountain1";
		default:
			throw new RuntimeException();
		}
	}
	
	
	
}