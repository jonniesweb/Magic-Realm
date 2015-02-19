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
		log.trace("get image: " + filename);
		
		try {
			// get image from file
			BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(filename + "1.gif"));
			
			BufferedImage rotatedImage = rotateImage(tile, image);
			
			// draw the image onto the gameboard
			graphic.drawImage(rotatedImage, (int) x, (int) y, (int) engine.getCellWidth(), (int) engine.getCellHeight(), null);
			
			// draw chits
			
			float bx = 413f / image.getWidth();
			float by = 217f / image.getHeight();
			
//			System.out.println(bx);
//			System.out.println(by);
			
			float zx = bx * engine.getCellWidth();
			float zy = by * engine.getCellHeight();
			
			
			Image innImage = ImageCache.getImage("inn");
			graphic.drawImage(innImage, (int) (x + zx - 25), (int) (y + zy - 25), 50, 50, null);
			
			
		} catch (IOException e) {
			log.error("Unable to load image: " + filename, e);
		}
		
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