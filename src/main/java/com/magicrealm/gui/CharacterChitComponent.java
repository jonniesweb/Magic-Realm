package com.magicrealm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.magicrealm.characters.MRCharacter;

public class CharacterChitComponent extends ChitComponent {

	private static final Color TAN = new Color(210, 180, 140);
	private boolean hidden = false;
	


	/**
	 * Create the panel.
	 */
	public CharacterChitComponent(MRCharacter chit, boolean hidden) {
		super(chit);
		this.hidden = hidden;
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		int preferredSize = getWidth();
		// draw circle background
		g2.fillOval(0, 0, preferredSize, preferredSize);
		if (hidden) {
			g2.setColor(Color.GREEN);
		} else {
			g2.setColor(TAN);
		}
		g2.fillOval(2, 2, preferredSize - 4, preferredSize - 4);
		
		// draw character image
		float center = preferredSize / 2;
		BufferedImage image = getImage();
		float cx = center - (image.getWidth() / 2);
		float cy = center - (image.getHeight() / 2);
		g2.drawImage(image, (int) cx, (int) cy, image.getWidth(), image.getHeight(), null);
	}
	
	
	
}
