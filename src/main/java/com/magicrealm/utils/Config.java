package com.magicrealm.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config extends Properties {
	private static final String CONFIG_FILENAME = "GameConfig.properties";
	private static final String DEV_CONFIG_FILENAME = "GameConfig.dev.properties";

	private static final Log log = LogFactory.getLog(Config.class);
	
	private static final Config singleton = new Config();
	private static boolean testMode = false;
	
	private Config() {
		loadConfigFromFile();
	}

	private void loadConfigFromFile() {
		String configFilename;
		if ("true".equals(System.getProperty("devMode", "false"))) {
			configFilename = DEV_CONFIG_FILENAME;
		} else {
			configFilename = CONFIG_FILENAME;
		}
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFilename);
		
		try {
			load(inputStream);
			log.info("Successfully loaded settings from " + configFilename);
			
		} catch (Exception e) {
			log.warn("No \"" + configFilename + "\" file found on classpath. Using defaults");
		}
		
	}
	
	public static String getString(String key, String defaultValue) {
		/*
		 * return the property from the system properties. If null, return the
		 * result of getting it from this property file.
		 */
		String systemProperty = System.getProperty(key);
		if (systemProperty != null)
			return systemProperty;
		else
			return singleton.getProperty(key);
	}
	
	public static int getInteger(String key, int defaultValue) {
		String result = singleton.getProperty(key);
		if (result != null) {
			return Integer.parseInt(result);
		} else
			return defaultValue;
	}
	
	public static boolean getBoolean(String key, boolean defaultValue) {
		String result = singleton.getProperty(key);
		if (result != null) {
			return Boolean.parseBoolean(result);
		} else
			return defaultValue;
	}
	
	@Override
	public String getProperty(String key) {
		if (testMode)
			return null;
		else
			return super.getProperty(key);
	}

	/**
	 * Enables test mode. Uses the default specified values instead of hitting
	 * the config file. Doesn't ignore system properties though.
	 * @param testMode
	 */
	public static void setTestMode(boolean testMode) {
		Config.testMode = testMode;
	}
}