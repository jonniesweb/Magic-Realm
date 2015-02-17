package com.magicrealm.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImageCache {
	
	private static final Log log = LogFactory.getLog(ImageCache.class);
	private static HashMap<String, Image> cache = new HashMap<String, Image>();
	
	/**
	 * Get an image from the cache. Loads image from disk if not loaded yet.
	 * @param name
	 * @return
	 */
	public static Image getImage(String name) {
		Image image = cache.get(name);
		try {
			if (image == null) {
				return loadImage(name);
			}
			return image;
			
		} catch (IOException e) {
			log.error("unable to load image", e);
		}
		
		return null;
	}

	/**
	 * Load an image from disk and put it in the cache
	 * @param name
	 * @return
	 * @throws IOException
	 */
	private static Image loadImage(String name) throws IOException {
		BufferedImage image = ImageIO.read(ImageCache.class.getClassLoader().getResource(name + "1.gif"));
		cache.put(name, image);
		return image;
	}
}
