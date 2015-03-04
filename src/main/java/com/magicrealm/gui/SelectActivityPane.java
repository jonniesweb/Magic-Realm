package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.magicrealm.GameState;
import com.magicrealm.models.Activity.ActivityType;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;

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
	private JComboBox<String> tileBox;
	private JComboBox<String> clearingBox;
	private TileClearing selectedClearing;
	
	public SelectActivityPane() {
		setLayout(new BorderLayout());
		
		group = new ActivityGroup();
		
		activityButtons = new JPanel();
		activityButtons.setLayout(new GridLayout(1, 5));
		
		move = new ActivityButton("Move", ActivityType.MOVE);
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!GameState.getInstance().getCharacter().isCheatModeEnabled()) {
					SimpleSelection clearing = new SimpleSelection(GameState.getInstance().getCharacter().getFutureClearing().getPlayerConnectedClearings(), "Select a clearing");
					selectedClearing = (TileClearing) clearing.getSelected();
				}
			}
		});
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
			
		if(GameState.getInstance().getCharacter().isCheatModeEnabled()) {
			tileBox = new JComboBox<String>(GameTile.getTileNames());
			tileBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					populateClearingBox();
				}
			});
			
			clearingBox = new JComboBox<String>();
			populateClearingBox();
			this.add(tileBox, BorderLayout.CENTER);
			this.add(clearingBox, BorderLayout.SOUTH);
		}
	}
	
	public void populateClearingBox() {
		String[] clearings = GameState.getInstance().getModel().getTile((String) tileBox.getSelectedItem()).getClearingStrings();
		clearingBox.setModel(new DefaultComboBoxModel<String>(clearings));
		clearingBox.updateUI();
		clearingBox.repaint();
		this.repaint();
		this.updateUI();
	}
	
	public ActivityType getActivityType() {
		return group.getSelectedActivityType();
	}

	public TileType getTileType() {
		if(GameState.getInstance().getCharacter().isCheatModeEnabled())
			return TileType.valueOf((String) tileBox.getSelectedItem());
		return GameState.getInstance().getModel().getTileFromClearing(selectedClearing).getTileType();
	}

	public int getClearingNumber() {
		if(GameState.getInstance().getCharacter().isCheatModeEnabled())
			return Integer.parseInt((String) clearingBox.getSelectedItem());
		return selectedClearing.getClearingNumber();
	}

}
