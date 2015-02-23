package com.magicrealm.gui;

import javax.swing.JOptionPane;

public class SimpleSelection {
	
	SelectObject object;
	
	public SimpleSelection(Object[] array, String heading) {
		object = new SelectObject(array);
		JOptionPane.showConfirmDialog(null, object, heading, JOptionPane.OK_CANCEL_OPTION);
	}
	
	public Object getSelected() {
		return object.getSelected();
	}

}
