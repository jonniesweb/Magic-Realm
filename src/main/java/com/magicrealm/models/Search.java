package com.magicrealm.models;

import javax.swing.JOptionPane;

import com.magicrealm.GameState;
import com.magicrealm.gui.SelectObject;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.models.tiles.TileClearing.ClearingType;
import com.magicrealm.utils.ProbabilityCalculator;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public class Search extends Activity {

	public Search() {
		this.activity = ActivityType.SEARCH;
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		SimpleSelection selectTable = new SimpleSelection(new Table[] {new Peer(), new Locate()}, "Select A Table");
		Table selectedTable = (Table) selectTable.getSelected();
		Result result = ProbabilityCalculator.getResult();
		if(result == Result.ONE) {
			SimpleSelection selectResult = new SimpleSelection(ProbabilityCalculator.getResultChoices(), "Select A Result");
			selectedTable.execute((Result) selectResult.getSelected());
		} else {
			selectedTable.execute(result);
		}
	}

}
