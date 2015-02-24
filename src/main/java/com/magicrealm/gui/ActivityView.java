package com.magicrealm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.magicrealm.models.MRCharacter;
import com.magicrealm.GameState;
import com.magicrealm.models.Activity;
import com.magicrealm.models.tiles.GameTile.TileType;

public class ActivityView extends JPanel {
		
		private JLabel label;
		private JButton selectActivity;
		private JButton execute;
	
	public ActivityView() {
		label = new JLabel("");
		
		selectActivity = new JButton("Select Activity");
		selectActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectActivityPane activity = new SelectActivityPane();
				int option = JOptionPane.showConfirmDialog(null, activity, "Select an Activity", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					GameState.getInstance().getCharacter().addActivity(Activity.buildActivity(activity.getActivityType(), activity));
					update(GameState.getInstance().getCharacter().getActivities());
				}
			}
		});
		execute = new JButton ("Daylight");
		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(GameState.getInstance().getCharacter().executeActivity() > 0) {
				}
				clearActivities();
			}
		});
		
		this.add(label);
		this.add(selectActivity);
		this.add(execute);
	}
	
	public void update(ArrayList<Activity> activities) {
		String s = "";
		for(Activity a: activities) {
			s += a.toString() + " - ";
		}
		label.setText(s);
	}
	
	public void clearActivities() {
		label.setText("");
	}

}
