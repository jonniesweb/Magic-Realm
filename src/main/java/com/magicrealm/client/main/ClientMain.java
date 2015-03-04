package com.magicrealm.client.main;

import javax.swing.JOptionPane;

import com.magicrealm.GameState;
import com.magicrealm.gui.BoardView;
import com.magicrealm.gui.SelectCharacter;
import com.magicrealm.gui.SimpleSelection;
import com.magicrealm.models.Activity;
import com.magicrealm.models.Amazon;
import com.magicrealm.models.Captain;
import com.magicrealm.models.Swordsman;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile.TileType;

public class ClientMain {
	public static void main(String[] args) {
		GameState instance = GameState.getInstance();
		instance.setModel(new DefaultMagicRealmHexEngineModel(0, 0));
		
		boolean cheatMode = false;
		int option = JOptionPane.showConfirmDialog(null, "Cheat Mode?", "Mode", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			cheatMode = true;
		}
		SelectCharacter selectCharacter = new SelectCharacter();
		JOptionPane.showConfirmDialog(null, selectCharacter, "Select a Character", JOptionPane.OK_OPTION);
		instance.setCharacter(selectCharacter.getSelectedCharacter());
		instance.getCharacter().setCheatModeEnabled(cheatMode);
		instance.getModel().placeChit(selectCharacter.getTileType(), 5, instance.getCharacter());
		instance.getModel().updateUI();
		
		new BoardView(instance.getModel());
		
		
	}
}
