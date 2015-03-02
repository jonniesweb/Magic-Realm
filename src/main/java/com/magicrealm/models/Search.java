package com.magicrealm.models;

import com.magicrealm.GameState;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.utils.ProbabilityCalculator;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public class Search extends Activity {

	public Search() {
		this.activity = ActivityType.SEARCH;
	}
	
	@Override
	public void execute(MRCharacter player) {
		// TODO Auto-generated method stub
		SimpleSelection selectTable = new SimpleSelection(new Table[] {new Peer(), new Locate(), new Loot()}, "Select A Table");
		Table selectedTable = (Table) selectTable.getSelected();
		Result result = ProbabilityCalculator.getResult();
		if(GameState.getInstance().getCheatMode() || result == Result.ONE && !(selectedTable instanceof Loot)) {
			Result[] choices = (selectedTable instanceof Loot) ? Result.values() : ProbabilityCalculator.getResultChoices();
			SimpleSelection selectResult = new SimpleSelection(choices, "Select A Result");
			selectedTable.execute((Result) selectResult.getSelected());
		} else {
			selectedTable.execute(result);
		}
	}

}
