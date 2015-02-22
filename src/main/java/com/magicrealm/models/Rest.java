package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.gui.SelectRestChit;


public class Rest extends Activity {
	
	public Rest() {
		this.activity = ActivityType.REST;
	}

	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		SelectRestChit restChit = new SelectRestChit(player.getFatiguedChits());
		JOptionPane.showConfirmDialog(null, restChit, "Select A Chit To Restore", JOptionPane.OK_OPTION);
		player.rest(restChit.getSelectedChit());
	}

}
