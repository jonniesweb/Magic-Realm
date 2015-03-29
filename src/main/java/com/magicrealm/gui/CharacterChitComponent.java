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
		
		// calculate size of the image based on the smallest edge
		int size = Math.min(getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw circle background
		g2.setColor(Color.BLACK);
		g2.fillOval(0, 0, size, size);
		if (hidden) {
			g2.setColor(Color.GREEN);
		} else {
			g2.setColor(TAN);
		}
		g2.fillOval(2, 2, size - 4, size - 4);
		
		// draw character image
		float center = size / 2;
		BufferedImage image = getImage();
		
		// get the scaling factor for the image to be scaled down to
		float scalingFactor = ((float) size) / preferredSize;
		
		// scale image width and height
		float sx = scalingFactor * image.getWidth();
		float sy = scalingFactor * image.getHeight();

		// determine position to draw image at based on scaled image
		float cx = center - (sx / 2);
		float cy = center - (sy / 2);

		// draw the image
		g2.drawImage(image, (int) cx, (int) cy, (int) sx, (int) sy, null);
	}
	
	
	
}
