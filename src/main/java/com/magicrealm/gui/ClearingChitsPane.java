package com.magicrealm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.Captain;
import com.magicrealm.characters.Swordsman;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.armors.Helmet;
import com.magicrealm.models.armors.Shield;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

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
		final ClearingChitsPane clearingChitsPane = new ClearingChitsPane();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(200, 400);
				frame.getContentPane()
						.add(clearingChitsPane);
				frame.repaint();
			}
		});
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				TileClearing clearing = new TileClearing(5);
				clearing.addChit(new Amazon());
				clearing.addChit(new Swordsman());
				clearing.addChit(new Shield());
				clearing.addChit(new Helmet());
				
				clearingChitsPane.update(new TileClearingLocation(TileType.B, 5), clearing);
			}
		});
	}

	/**
	 * Update the panel with the chits from the given clearing.
	 * @param location
	 * @param closest
	 */
	public void update(TileClearingLocation location, TileClearing closest) {
		removeAll();
		for (Placeable chit : closest.getChits()) {
			add(ChitComponent.create(chit));
		}
	}
}
