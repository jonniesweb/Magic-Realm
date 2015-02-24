package com.magicrealm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GameLog {
	
	private static final Log log = LogFactory.getLog(GameLog.class);
	
	private static final Document document = new DefaultStyledDocument();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm:ss");
	
	private GameLog() {
		// prevent instantiation
	}
	
	/**
	 * Log <code>s</code> to both the console and the logfiles
	 * @param s
	 */
	public static void log(String s) {
		consoleLog(s);
		log.info("Console log: " + s);
	}

	private static void consoleLog(String s) {
		try {
			String date = dateFormat.format(new Date());
			
			document.insertString(document.getLength(), "\n" + date + ": " + s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Log <code>gameConsole</code> to the game console and <code>logs</code> to the logfiles
	 * @param gameConsole
	 * @param logs
	 */
	public static void log(String gameConsole, String logs) {
		consoleLog(gameConsole);
		log.info(logs);
	}

	public static Document getDocument() {
		return document;
	}
}
