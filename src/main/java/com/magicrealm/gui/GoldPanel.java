package com.magicrealm.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for displaying the gold amount.
 */
public class GoldPanel extends JPanel {

	private JLabel goldLabel;

	public GoldPanel() {
		goldLabel = new JLabel("Gold: 0");
		add(goldLabel);
	}
	
	/**
	 * Update the label with the given gold amount
	 * @param goldAmount
	 */
	public void updateGold(int goldAmount) {
		goldLabel.setText("Gold: " + goldAmount);
	}
}
