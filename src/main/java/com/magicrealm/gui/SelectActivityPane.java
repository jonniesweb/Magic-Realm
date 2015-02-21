package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.magicrealm.models.Activity.ActivityType;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;

public class SelectActivityPane extends JPanel{
	
	private class ActivityButton extends JToggleButton {
		private ActivityType type;
		
		public ActivityButton(String text, ActivityType type) {
			this.setText(text);
			this.type = type;
		}
		
		public ActivityType getType() {
			return type;
		}
		
		public String toString() {
			return type.name();
		}
	}
	
	private class ActivityGroup extends ButtonGroup {
		
		public ActivityType getSelectedActivityType() {
			Enumeration<AbstractButton> itr = this.getElements();
			while(itr.hasMoreElements()) {
				ActivityButton a = (ActivityButton) itr.nextElement();
				if(a.isSelected())
					return a.getType();
			}
			return null;
		}
		
	}
	
	private ActivityButton move;
	private ActivityButton hide;
	private ActivityButton search;
	private ActivityButton rest;
	private ActivityGroup group;
	private JPanel activityButtons;
	private ActivityType activity;
	private JComboBox<String> tileBox;
	private JComboBox<String> clearingBox;

	private String[] tiles;
	
	public SelectActivityPane() {
		setLayout(new BorderLayout());
		
		tiles = GameTile.getTileNames();
		group = new ActivityGroup();
		
		activityButtons = new JPanel();
		activityButtons.setLayout(new GridLayout(1, 3));
		
		move = new ActivityButton("Move", ActivityType.MOVE);
		group.add(move);
		activityButtons.add(move);
		
		hide = new ActivityButton("Hide", ActivityType.HIDE);
		group.add(hide);
		activityButtons.add(hide);
		
		search = new ActivityButton("Search", ActivityType.SEARCH);
		group.add(search);
		activityButtons.add(search);
		
		rest = new ActivityButton("Rest", ActivityType.REST);
		group.add(rest);
		activityButtons.add(rest);
		
		this.add(activityButtons, BorderLayout.NORTH);
		
		tileBox = new JComboBox<String>(tiles);
		
		clearingBox = new JComboBox<String>(new String[] {"1", "2", "3", "4", "5"});
		
		this.add(tileBox, BorderLayout.CENTER);
		this.add(clearingBox, BorderLayout.SOUTH);
	}
	
	public ActivityType getActivityType() {
		return group.getSelectedActivityType();
	}
	
	public TileType getTileType() {
		return GameTile.TileType.valueOf((String) tileBox.getSelectedItem());
	}

	public int getClearingNumber() {
		return Integer.parseInt((String) clearingBox.getSelectedItem());
	}

}
