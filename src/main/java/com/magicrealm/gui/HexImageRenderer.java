package com.magicrealm.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.renders.swing.ColorHexRender;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;
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
		
		if (tile.getTileType() == TileType.EV) {
			// show 2 ghosts on 5
			drawChitImage(graphic, x, y, getX(engine, image, 143f), getY(engine, image, 143f), "ghost");
			drawChitImage(graphic, x, y, getX(engine, image, 133f), getY(engine, image, 133f), "ghost");
		}
		
		// draw all chits from the clearings
		for (TileClearing clearing : tile.getClearings()) {
			for (Placeable chit : clearing.getChits()) {
				Point rotatedPoint = rotateCoordinates(clearing.getXPosition(), clearing.getYPosition(), tile.getRotation());
				
				Point scaleCoordinates = scaleCoordinates(engine, rotatedPoint);
				
				drawChitImage(graphic, x, y, scaleCoordinates.x, scaleCoordinates.y, chit.getImageName());
			}
		}
		
		// draw all map chits
		if (tile.getWarningChit() != null && tile.getWarningChitPosition() != null) {
			drawMapChit(graphic, engine, x, y, tile, tile.getWarningChitPosition(), tile.getWarningChit());
		}
		
		// draw site or sound chit
		if (tile.getSiteSoundChit() != null && tile.getSiteSoundChitPosition() != null) {
			drawMapChit(graphic, engine, x, y, tile, tile.getSiteSoundChitPosition(), tile.getSiteSoundChit());
		}
		
	}
	
	public static Point scaleCoordinates(HexEngine<Graphics2D> engine, Point point) {
		double xMagnitude = point.getX() / (double) GameTile.MAX_X;
		double yMagnitude = point.getY() / (double) GameTile.MAX_Y;
		
		return new Point((int) (xMagnitude * engine.getCellWidth()), (int) (yMagnitude * engine.getCellHeight()));
	}
	
	public void drawMapChit(Graphics2D graphic, HexEngine<Graphics2D> engine, float x, float y, GameTile tile, Point point, MapChit chit) {

		BufferedImage mapChit = getMapChit(chit);
		Point scaledRotatedCoords = scaleCoordinates(engine, rotateCoordinates(point, tile.getRotation()));
		
		int sx = (int) (scaledRotatedCoords.x);
		int sy = (int) (scaledRotatedCoords.y);
		
		graphic.drawImage(mapChit, (int) x + sx - 10, (int) y + sy - 10, 20, 20, null);
	}

	private void drawChitImage(Graphics2D graphic, double x, double y,
			double d, double e, String imageName) {
		graphic.drawImage(ImageCache.getImage(imageName), (int) (x + d - 25),
				(int) (y + e - 25), 50, 50, null);
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

		Graphics2D rotatedImageGraphics = rotatedImage.createGraphics();
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
	
	public static Point rotateCoordinates(Point point, int rotation) {
		return rotateCoordinates((int) point.getX(), (int) point.getY(), rotation);
	}
	
	public static Point rotateCoordinates(int x, int y, int rotation) {
		if (rotation == 0)
			return new Point(x, y);
		
		double xhalf = GameTile.MAX_X / 2d;
		double yhalf = GameTile.MAX_Y / 2d;
		double theta = Math.toRadians(60 * rotation);
		x -= xhalf;
		y -= yhalf;
		double rx = (x * Math.cos(theta)) - (y * Math.sin(theta));
		double ry = (x * Math.sin(theta)) + (y * Math.cos(theta));
		
		rx += xhalf;
		ry += yhalf;
		
		return new Point((int) rx, (int) ry);
		
	}
	
	private BufferedImage getMapChit(MapChit chit) {
		
		BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 20, 20);
		g.setColor(Color.black);
		if (chit instanceof WarningChit)
			g.drawString("W", 5, 15);
		else
			g.drawString("S", 5, 15);
		
		return image;
		
	}
}