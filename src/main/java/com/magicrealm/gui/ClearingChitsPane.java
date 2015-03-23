package com.magicrealm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.Captain;
import com.magicrealm.characters.Swordsman;

public class ClearingChitsPane extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public ClearingChitsPane() {
		add(new CharacterChitComponent(new Amazon(), true));
		add(new CharacterChitComponent(new Swordsman(), false));
		add(new CharacterChitComponent(new Captain(), false));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(200, 400);
				frame.getContentPane()
						.add(new ClearingChitsPane());
				frame.repaint();
			}
		});
	}
}
