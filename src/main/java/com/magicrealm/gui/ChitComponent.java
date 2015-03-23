package com.magicrealm.gui;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.magicrealm.models.Placeable;

public class ChitComponent extends JComponent {
	
	private BufferedImage image;
	private Placeable chit;
	
	/**
	 * Create the panel.
	 */
	public ChitComponent(Placeable chit) {
		this.chit = chit;
		
	}
	
}
