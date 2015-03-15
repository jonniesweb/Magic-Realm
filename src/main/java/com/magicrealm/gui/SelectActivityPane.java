package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.magicrealm.activity.Activity;
import com.magicrealm.activity.Activity.ActivityType;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.GameTile.TileType;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class SelectActivityPane extends JPanel {
	
	private ActivityGroup group;
	private JPanel activityButtons;
	private JComboBox<String> tileBox;
	private JComboBox<String> clearingBox;
	private TileClearingLocation selectedClearing;
	
	public SelectActivityPane() {
		setLayout(new BorderLayout());
		
		group = new ActivityGroup();
		
		activityButtons = new JPanel();
		activityButtons.setLayout(new GridLayout(1, 5));
		
		ArrayList<ActivityButton> activities = new ArrayList<SelectActivityPane.ActivityButton>();
		
		ActivityType[] activityTypes = Activity.ActivityType.values();
		
		ActivityButton ab;
		for(ActivityType at: activityTypes) {
			ab = new ActivityButton(at.name().toLowerCase(), at);
			if(at == ActivityType.MOVE)
				ab.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ClientGameState instance = ClientGameState.getInstance();
						
						if(!instance.getCharacter().isCheatModeEnabled()) {
							TileClearingLocation futureLocation = instance.getActivities().getClearing();
							TileClearing futureClearing = instance.getModel().getClearing(futureLocation);
							
							ArrayList<TileClearingLocation> locations = new ArrayList<TileClearingLocation>();
							for(TileClearing tc: futureClearing.getPlayerConnectedClearings(instance.getCharacter())) {
								locations.add(instance.getModel().getTileClearingLocation(tc));
							}
							
							SimpleSelection clearing = new SimpleSelection(locations.toArray(new TileClearingLocation[0]), "Select a clearing");
							selectedClearing = (TileClearingLocation) clearing.getSelected();
						}
					}
				});
			group.add(ab);
			activityButtons.add(ab);
		}
		
		this.add(activityButtons, BorderLayout.NORTH);
			
		if(ClientGameState.getInstance().getCharacter().isCheatModeEnabled()) {
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
		String[] clearings = ClientGameState.getInstance().getModel().getTile((String) tileBox.getSelectedItem()).getClearingStrings();
		clearingBox.setModel(new DefaultComboBoxModel<String>(clearings));
		clearingBox.updateUI();
		clearingBox.repaint();
		this.repaint();
		this.updateUI();
	}
	
	public ActivityType getActivityType() {
		return group.getSelectedActivityType();
	}
	
	public TileClearingLocation getMoveLocation() {
		if(ClientGameState.getInstance().getCharacter().isCheatModeEnabled()) {
			TileType t = TileType.valueOf((String) tileBox.getSelectedItem());
			int c = Integer.parseInt((String) clearingBox.getSelectedItem());
			return new TileClearingLocation(t, c);
		}
		return selectedClearing;
	}
	
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

}
