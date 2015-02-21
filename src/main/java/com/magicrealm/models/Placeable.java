package com.magicrealm.models;

import java.awt.Image;

/**
 * Allows whatever implements this to be placed in a tile clearing.
 */
public interface Placeable {
	public Image getImage();

	public String getImageName();
}
