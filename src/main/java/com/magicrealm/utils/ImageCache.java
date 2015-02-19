package com.magicrealm.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImageCache {
	
	private static final Log log = LogFactory.getLog(ImageCache.class);
	private static HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
	
	/**
	 * Get an image from the cache. Loads image from disk if not loaded yet.
	 * @param name
	 * @return
	 */
	public static BufferedImage getImage(String name) {
		BufferedImage image = cache.get(name);
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
	private static BufferedImage loadImage(String name) throws IOException {
		BufferedImage image = ImageIO.read(ImageCache.class.getClassLoader().getResource(name + ".gif"));
		cache.put(name, image);
		return image;
	}
}
