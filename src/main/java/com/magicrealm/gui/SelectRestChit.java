package com.magicrealm.gui;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.magicrealm.models.ActionChit;

public class SelectRestChit extends JPanel {
	
	private JComboBox<ActionChit> chitBox;
	
	public SelectRestChit(ActionChit[] chits) {
		setLayout(new BorderLayout());
		
		chitBox = new JComboBox<ActionChit>(chits);
		
		this.add(chitBox);
	}
	
	public ActionChit getSelectedChit() {
		return (ActionChit) chitBox.getSelectedItem();
	}

}
