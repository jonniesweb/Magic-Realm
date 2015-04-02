package com.magicrealm.gui;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SelectObject extends JPanel {
	
	private JComboBox<Object> objBox;
	
	public SelectObject(Object[] objs) {
		setLayout(new BorderLayout());
		
		objBox = new JComboBox<Object>(objs);
		
		this.add(objBox);
	}
	
	public Object getSelected() {
		return objBox.getSelectedItem();
	}
	
	public void setSelected(Object newObj) {
		objBox.setSelectedItem(newObj);
	}

}
