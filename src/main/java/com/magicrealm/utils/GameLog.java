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
	
	public static void log(String s) {
		try {
			String date = dateFormat.format(new Date());
			
			log.info("Console log: " + s);
			document.insertString(document.getLength(), "\n" + date + ": " + s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static Document getDocument() {
		return document;
	}
}
