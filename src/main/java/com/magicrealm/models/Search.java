package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.gui.SelectObject;
import com.magicrealm.utils.ProbabilityCalculator;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public class Search extends Activity {

	public Search() {
		this.activity = ActivityType.SEARCH;
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		Peer peer = new Peer();
		Locate locate = new Locate();
		SelectObject selectTable = new SelectObject(new Table[] {peer, locate});
		int option = JOptionPane.showConfirmDialog(null, selectTable, "Select A Table", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION) {
			Result result = ProbabilityCalculator.getResult();
			((Table) selectTable.getSelected()).execute(result);
		}
	}

}
