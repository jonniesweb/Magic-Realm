package com.magicrealm.client.main;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.exceptions.GameAlreadyStartedException;
import com.magicrealm.gui.BoardView;
import com.magicrealm.gui.SelectCharacter;
import com.magicrealm.models.ClientGameState;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.board.DefaultMagicRealmHexEngineModel;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.networking.INet;
import com.magicrealm.networking.RMIClient;

public class ClientMain {
	
	private static final Log log = LogFactory.getLog(ClientMain.class);
	
	public static void main(String[] args) {
		ClientGameState instance = ClientGameState.getInstance();
		instance.setModel(new DefaultMagicRealmHexEngineModel(0, 0));
		
		RMIClient rmiClient = new RMIClient("localhost", 1099);
		rmiClient.start();
		INet service = rmiClient.getService();
		
		boolean cheatMode = false;
		int option = JOptionPane.showConfirmDialog(null, "Enable cheat mode?", "Mode", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			cheatMode = true;
		}
		SelectCharacter selectCharacter = new SelectCharacter();
		JOptionPane.showConfirmDialog(null, selectCharacter, "Select a Character", JOptionPane.OK_OPTION);
		instance.setCharacter(selectCharacter.getSelectedCharacter());
		
		try {
			MRCharacter character = service.selectCharacter(selectCharacter.getSelectedCharacter().getCharacterType());
			instance.setCharacter(character);
		} catch (CharacterAlreadyTakenException e) {
			// TODO Prompt user to select another character to play as
			log.error(e);
		}
		
		log.info("setting cheat mode to " + cheatMode);
		instance.getCharacter().setCheatModeEnabled(cheatMode);
		service.setCheatModeForCharacter(cheatMode);
//		instance.getModel().placeChit(selectCharacter.getTileType(), 5, instance.getCharacter());
		MRCharacter character = service.setStartingLocationForCharacter(selectCharacter.getStartingLocation());
		instance.setCharacter(character);
		instance.getModel().updateUI();
		
		try {
			service.startGame();
		} catch (GameAlreadyStartedException e) {
			e.printStackTrace();
		}
		
		MagicRealmHexEngineModel model = service.getGameBoard();
		
		new BoardView(model);
		
		
	}
}
