package com.magicrealm.activity;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.tables.Locate;
import com.magicrealm.tables.Loot;
import com.magicrealm.tables.Peer;
import com.magicrealm.tables.Table;
import com.magicrealm.utils.ProbabilityCalculator;
import com.magicrealm.utils.ProbabilityCalculator.Result;

public class Search extends Activity {
	
	public enum search { Peer, Locate, Loot }

	public Search() {
		this.activity = ActivityType.SEARCH;
	}
	
	@Override
	public void execute(ServerGameState gameState, String clientId) {
		MRCharacter character = gameState.getCharacter(clientId);
		IClientService clientService = gameState.getClientService(clientId);

		search selectedSearch = (search) clientService.clientSelect(search.values(), "Select a search method");
		Table table = getTableFromSearchType(selectedSearch, gameState, clientId);
		Result result = ProbabilityCalculator.getResult(clientId);
		
		if(result == Result.ONE && !(table instanceof Loot)) {
			Result[] choices = (table instanceof Loot) ? Result.values() : ProbabilityCalculator.getResultChoices();
			Result selectResult = (Result) clientService.clientSelect(choices, "Select which one to loot");
			table.execute(selectResult);
		} else {
			table.execute(result);
		}
		
		// old stuff
//		SimpleSelection selectTable = new SimpleSelection(new Table[] {new Peer(), new Locate(), new Loot()}, "Select A Table");
//		Table selectedTable = (Table) selectTable.getSelected();
//		Result result = ProbabilityCalculator.getResult();
//		if(result == Result.ONE && !(selectedTable instanceof Loot)) {
//			Result[] choices = (selectedTable instanceof Loot) ? Result.values() : ProbabilityCalculator.getResultChoices();
//			SimpleSelection selectResult = new SimpleSelection(choices, "Select A Result");
//			selectedTable.execute((Result) selectResult.getSelected());
//		} else {
//			selectedTable.execute(result);
//		}
	}

	private Table getTableFromSearchType(search selectedSearch, ServerGameState state, String clientId) {
		switch (selectedSearch) {
		case Locate:
			return new Locate(state, clientId);
		case Loot:
			return new Loot(state, clientId);
		case Peer:
			return new Peer(state, clientId);
		default:
			throw new RuntimeException("unable to create table for search type " + selectedSearch); 
		}
	}

}
