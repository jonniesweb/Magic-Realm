package com.magicrealm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.magicrealm.activity.Activity;
import com.magicrealm.activity.Move;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.utils.GameLog;

public class ActivityView extends JPanel {
		
		private JLabel label;
		private JButton selectActivity;
		private JButton execute;
	
	public ActivityView() {
		final ClientGameState instance = ClientGameState.getInstance();
		label = new JLabel("");
		
		selectActivity = new JButton("Select Activity");
		
		selectActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectActivityPane activity = new SelectActivityPane();
				int option = JOptionPane.showConfirmDialog(null, activity, "Select an Activity", JOptionPane.OK_CANCEL_OPTION);
				
				if (activity.getActivityType() == null)
					return;
				
				if(option == JOptionPane.OK_OPTION) {
					Activity a = Activity.buildActivity(activity.getActivityType(), activity);
					
					boolean result = instance.getActivities().addActivity(a);
					if(result) {
						update(instance.getActivities().getQueuedActivities());
					} else {
						GameLog.log("Not enough slots for that activity");
					}
					
				}
			}
		});
		execute = new JButton ("Daylight");
		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						instance.getService().setActivities(
								instance.getActivities());		
						clearActivities();
					}
				}).start();
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
