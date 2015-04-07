package com.magicrealm.client.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.exceptions.CharacterAlreadyTakenException;
import com.magicrealm.gui.SelectCharacter;
import com.magicrealm.gui.SetupChitsPanel;
import com.magicrealm.gui.StartGameFrame;
import com.magicrealm.networking.RMIClient;

public class ClientMain {
	
	private static final Log log = LogFactory.getLog(ClientMain.class);
	
	public static void main(String[] args) {
		ClientGameState instance = ClientGameState.getInstance();
		RMIClient rmiClient = new RMIClient("localhost", 1099);
		rmiClient.start();
		instance.setService(rmiClient.getService());
		
		boolean cheatMode = false;
		int option = JOptionPane.showConfirmDialog(null, "Enable cheat mode?", "Mode", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			cheatMode = true;
		}
		
		// if cheat mode enabled, ask if they want to setup the chits
		if (cheatMode) {
			int mapChitResult = JOptionPane.showConfirmDialog(null, "Specify map chits?", "Mode", JOptionPane.YES_NO_OPTION);
			if (mapChitResult == JOptionPane.YES_OPTION) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						SetupChitsPanel panel = new SetupChitsPanel(null);
						JFrame frame = new JFrame(
								"Specify map chits for the game board");
						frame.getContentPane().add(panel);
						frame.pack();
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
					}
				});
			}
		}
		
		SelectCharacter selectCharacter = new SelectCharacter();
		JOptionPane.showConfirmDialog(null, selectCharacter, "Select a Character", JOptionPane.OK_OPTION);
		instance.setCharacter(selectCharacter.getSelectedCharacter());
		
		try {
			MRCharacter character = instance.getService().selectCharacter(selectCharacter.getSelectedCharacter().getCharacterType());
			instance.setCharacter(character);
		} catch (CharacterAlreadyTakenException e) {
			// TODO Prompt user to select another character to play as
			log.error(e);
		}
		
		log.info("setting cheat mode to " + cheatMode);
		instance.getCharacter().setCheatModeEnabled(cheatMode);
		instance.getService().setCheatModeForCharacter(cheatMode);
//		instance.getModel().placeChit(selectCharacter.getTileType(), 5, instance.getCharacter());
		MRCharacter character = instance.getService().setStartingLocationForCharacter(selectCharacter.getStartingLocation());
		instance.setCharacter(character);
		
		// show the waiting for players/start game window
		StartGameFrame startGameFrame = new StartGameFrame(instance);
		instance.setStartGameFrame(startGameFrame);
		startGameFrame.setVisible(true);
		
	}
}
