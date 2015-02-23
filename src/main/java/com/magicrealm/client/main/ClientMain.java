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
		
		SelectCharacter selectCharacter = new SelectCharacter();
		JOptionPane.showConfirmDialog(null, selectCharacter, "Select a Character", JOptionPane.OK_OPTION);
		GameState.getInstance().setCharacter(selectCharacter.getSelectedCharacter());
		GameState.getInstance().getModel().placeChit(selectCharacter.getTileType(), 5, GameState.getInstance().getCharacter());
		GameState.getInstance().getModel().updateUI();
		
		new BoardView(GameState.getInstance().getModel());
		
		
	}
}
