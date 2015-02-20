package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.magicrealm.models.Activity;
import com.magicrealm.models.Activity.ActivityType;
import com.magicrealm.models.tiles.GameTile;

public class SelectActivityPane extends JPanel{
	
	private JButton move;
	private JButton hide;
	private JButton search;
	private JPanel activityButtons;
	private ActivityType activity;
	private String[] tiles;
	
	public SelectActivityPane() {
		setLayout(new BorderLayout());
		
		tiles = GameTile.getTileNames();
		
		activityButtons = new JPanel();
		activityButtons.setLayout(new GridLayout(1, 3));
		
		JButton move = new JButton("Move");
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.MOVE;
			}
		});
		activityButtons.add(move);
		
		JButton hide = new JButton("Hide");
		hide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.HIDE;
			}
		});
		activityButtons.add(hide);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.SEARCH;
			}
		});
		activityButtons.add(search);
		
		this.add(activityButtons, BorderLayout.NORTH);
		
		JComboBox<String> tileBox = new JComboBox<String>(tiles);
//		for (int i = 0; i < 4; i++)
//		      tileBox.addItem(tiles[i]);
		
		JComboBox<String> clearingBox = new JComboBox<String>(new String[] {"1", "2", "3", "4", "5", "6"});
		
		this.add(tileBox, BorderLayout.CENTER);
		this.add(clearingBox, BorderLayout.SOUTH);
		
	}
	
	public ActivityType getActivity() {
		return activity;
	}

}
