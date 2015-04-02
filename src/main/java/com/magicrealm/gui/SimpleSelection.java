package com.magicrealm.gui;

import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;

public class SimpleSelection {
	
	SelectObject object;
	
	public SimpleSelection(Object[] array, String heading) {
		this(array, heading, JOptionPane.OK_CANCEL_OPTION);
	}
	
	public SimpleSelection(Object[] array, String heading, int type) {
		object = new SelectObject(array);
		int option = JOptionPane.showConfirmDialog(null, object, heading, type);
		if(type == JOptionPane.YES_NO_OPTION && option == JOptionPane.NO_OPTION)
			object.setSelected(null);
	}
	
	public Object getSelected() {
		return object.getSelected();
	}

}
