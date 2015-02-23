package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.gui.SelectObject;


public class Rest extends Activity {
	
	public Rest() {
		this.activity = ActivityType.REST;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		SelectObject restChit = new SelectObject(player.getFatiguedChits());
		int option = JOptionPane.showConfirmDialog(null, restChit, "Select A Chit To Restore", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION) {
			player.rest((ActionChit) restChit.getSelected());
		}
	}
}
