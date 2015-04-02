package com.magicrealm.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for displaying simple text based info.
 */
public class StatPanel extends JPanel {

	private JLabel label;

	public StatPanel(String initialText) {
		label = new JLabel(initialText);
		add(label);
	}
	
	/**
	 * Set the text of the label that this pane contains
	 * @param text
	 */
	public void setText(String text) {
		label.setText(text);
	}
}
