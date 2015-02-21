package com.magicrealm.client.main;

import com.magicrealm.GameState;
import com.magicrealm.gui.BoardView;
import com.magicrealm.models.Amazon;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile.TileType;

public class ClientMain {
	public static void main(String[] args) {
		GameState.getInstance().setModel(new DefaultMagicRealmHexEngineModel(0, 0));
		GameState.getInstance().setCharacter(new Amazon());
		new BoardView(GameState.getInstance().getModel());
		
		
		GameState.getInstance().getModel().placeChit(TileType.BV, 5, GameState.getInstance().getCharacter());

	}
}
