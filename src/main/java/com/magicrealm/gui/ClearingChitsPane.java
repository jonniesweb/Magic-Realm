package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.magicrealm.characters.Amazon;
import com.magicrealm.characters.Swordsman;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.armors.Helmet;
import com.magicrealm.models.armors.Shield;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class ClearingChitsPane extends JPanel {
	
	private Box box;
	private JLabel clearingLabel;
	
	/**
	 * Create the panel.
	 */
	public ClearingChitsPane() {
		setLayout(new BorderLayout());
		
		clearingLabel = new JLabel("", SwingConstants.CENTER);
		add(clearingLabel, BorderLayout.NORTH);
		
		box = Box.createVerticalBox();
		JScrollPane chitScrollPane = new JScrollPane(box);
		add(chitScrollPane, BorderLayout.CENTER);
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
				frame.getContentPane().add(clearingChitsPane);
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
				clearingChitsPane.validate();
				clearingChitsPane.repaint();
			}
		});
	}

	/**
	 * Update the panel with the chits from the clearing
	 * @param location
	 * @param closest
	 */
	public void update(TileClearingLocation location, TileClearing closest) {
		
		// remove all current chits from the panel
		removeAllChits();
		
		// update the header text
		clearingLabel.setText(location.getTileType() + " " + location.getClearingNumber());
		
		// add all chits from the given clearing
		for (Placeable chit : closest.getChits()) {
			box.add(ChitComponent.create(chit));
		}
		validate();
		repaint();
	}

	private void removeAllChits() {
		box.removeAll();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(125, 100);
	}
}
