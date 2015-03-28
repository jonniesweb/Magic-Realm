package com.magicrealm.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.Placeable;
import com.magicrealm.utils.ImageCache;

/**
 * Base class for drawing rich-looking chits. use {@link #create(Placeable)} to
 * delegate creation of a {@link ChitComponent} customized to the chit type.
 */

public abstract class ChitComponent extends JComponent {
	
	private BufferedImage image;
	private Placeable chit;
	private int preferredSize;
	
	/**
	 * Create the panel.
	 */
	public ChitComponent(Placeable chit) {
		this.chit = chit;
		
		image = ImageCache.getImage(chit.getImageName());

		preferredSize = Math.max(image.getWidth(), image.getHeight()) + 15;
		
		setMaximumSize(new Dimension(preferredSize, preferredSize));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// draw character image
		float center = preferredSize/2;
		float cx = center - (image.getWidth() / 2);
		float cy = center - (image.getHeight() / 2);
		g2.drawImage(image, (int) cx, (int) cy, image.getWidth(), image.getHeight(), null);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredSize, preferredSize);
	}
	
	@Override
	public int getWidth() {
		return preferredSize;
	}
	
	@Override
	public int getHeight() {
		return preferredSize;
	}
	
	protected BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Create a {@link ChitComponent} instance customized to the type of chit.
	 * @param chit
	 * @return
	 */
	public static ChitComponent create(Placeable chit) {
		// if chit is a character
		if (chit instanceof MRCharacter) {
			MRCharacter character = (MRCharacter) chit;
			return new CharacterChitComponent(character, character.isHidden());
			
		} else {
			// default case
			return new ItemChitComponent(chit);
		}
	}
}
