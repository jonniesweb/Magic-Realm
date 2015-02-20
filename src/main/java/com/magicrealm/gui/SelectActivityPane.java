package com.magicrealm.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.magicrealm.models.Activity;
import com.magicrealm.models.Activity.ActivityType;

public class SelectActivityPane extends JPanel{
	
	private JButton move;
	private JButton hide;
	private JButton search;
	private ActivityType activity;
	
	public SelectActivityPane() {
		setLayout(new GridLayout(1, 3));
		
		JButton move = new JButton("Move");
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.MOVE;
			}
		});
		this.add(move);
		
		JButton hide = new JButton("Hide");
		hide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.HIDE;
			}
		});
		this.add(hide);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity = Activity.ActivityType.SEARCH;
			}
		});
		this.add(search);
		
	}
	
	public ActivityType getActivity() {
		return activity;
	}

}
