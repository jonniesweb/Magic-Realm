package com.magicrealm.gui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.renders.swing.ColorHexRender;


public final class HexImageRenderer extends ColorHexRender {
	// 497px by 431px
	static BufferedImage image;
	
	public HexImageRenderer() {
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource("ruins1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void renderHexCell(HexEngine<Graphics2D> engine, Graphics2D g,
			float x, float y, int col, int row) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.drawImage(image, (int) x, (int) y, (int) engine.getCellWidth(), (int) engine.getCellHeight(), null);
	}
	
	
	
}