package com.magicrealm.client.main;

import com.magicrealm.GameState;
import com.magicrealm.gui.ActivityView;
import com.magicrealm.gui.BoardView;
import com.magicrealm.models.Amazon;
import com.magicrealm.models.Character;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;

public class ClientMain {
	public static void main(String[] args) {
		Character character = new Amazon();
		ActivityView activityView = new ActivityView();
		
		GameState.getInstance().setModel(new DefaultMagicRealmHexEngineModel(0, 0));
		BoardView board = new BoardView(GameState.getInstance().getModel());
	}
}
