package com.magicrealm.client.main;

import javax.swing.JOptionPane;

import com.magicrealm.GameState;
import com.magicrealm.gui.BoardView;
import com.magicrealm.gui.SelectCharacter;
import com.magicrealm.models.Activity;
import com.magicrealm.models.Amazon;
import com.magicrealm.models.Captain;
import com.magicrealm.models.Swordsman;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile.TileType;

public class ClientMain {
	public static void main(String[] args) {
		GameState.getInstance().setModel(new DefaultMagicRealmHexEngineModel(0, 0));
		
		new BoardView(GameState.getInstance().getModel());
		

	}
}
