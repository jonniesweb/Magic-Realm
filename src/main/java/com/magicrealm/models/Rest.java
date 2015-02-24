package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.gui.SelectObject;
import com.magicrealm.utils.GameLog;


public class Rest extends Activity {
	
	public Rest() {
		this.activity = ActivityType.REST;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		if(player.getFatiguedChits().length > 0) {
			SelectObject restChit = new SelectObject(player.getFatiguedChits());
			int option = JOptionPane.showConfirmDialog(null, restChit, "Select A Chit To Restore", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				player.rest((ActionChit) restChit.getSelected());
			}
		} else {
			GameLog.log("No action chits to rest");
		}
	}
}
