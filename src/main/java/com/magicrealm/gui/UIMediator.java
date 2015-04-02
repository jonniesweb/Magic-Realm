package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class UIMediator {
	
	private final Log log = LogFactory.getLog(UIMediator.class);
	
	private ClientGameState gameState = ClientGameState.getInstance();
	private NewBoardComponent newBoardComponent;
	private ClearingChitsPane clearingChitsPane;
	private GameFrame gameFrame;
	private ActivityView activityView;
	private StatPanel goldPanel;
	private StatPanel famePanel;
	private StatPanel notorietyPanel;
	private ConsoleLog consoleLog;
	
	/**
	 * Mediator for controlling the UI elements and dialogs.
	 */
	public UIMediator() {
		
		// register a listener on the ClientGameState to update the model when it has changed
		gameState.getPcs().addPropertyChangeListener("model", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent) {
				MagicRealmHexEngineModel model = (MagicRealmHexEngineModel) paramPropertyChangeEvent.getNewValue();
				log.debug("update model");
				updateModel(model);
			}
		});
		
		gameState.getPcs().addPropertyChangeListener("character", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				MRCharacter character = (MRCharacter) evt.getNewValue();
				log.debug("update character");
				updateCharacter(character);
			}
		});
	}

	/**
	 * Update the chits in clearing pane with the latest clearing
	 * @param closest
	 */
	public void clearingSelected(final TileClearingLocation location, final TileClearing closest) {
		log.debug("updating clearingChitPane");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				clearingChitsPane.update(location, closest);
			}
		});
	}
	
	
	/**
	 * Initialize all of the widgets that this mediator mediates.
	 */
	public void createWidgets(MagicRealmHexEngineModel model) {
		clearingChitsPane = new ClearingChitsPane();
		gameFrame = new GameFrame();
		newBoardComponent = new NewBoardComponent(this, model);
		activityView = new ActivityView();
		goldPanel = new StatPanel("Gold: 0");
		notorietyPanel = new StatPanel("Notoriety: 0");
		famePanel = new StatPanel("Fame: 0");
		consoleLog = new ConsoleLog();
		
		JPanel topPanel = new JPanel(new FlowLayout());
		
		/*
		 * assemble widgets in the gameFrame
		 */
		
		// add boardComponent to a scrollpane
		JScrollPane boardScrollPane = new JScrollPane(newBoardComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		boardScrollPane.setPreferredSize(new Dimension(1000, 600));
		
		gameFrame.setLayout(new BorderLayout());
		
		// add widgets to the gameFrame
		gameFrame.add(boardScrollPane, BorderLayout.CENTER);
		
		topPanel.add(activityView);
		topPanel.add(goldPanel);
		topPanel.add(notorietyPanel);
		topPanel.add(famePanel);
		gameFrame.add(topPanel, BorderLayout.NORTH);
		
		gameFrame.add(consoleLog, BorderLayout.SOUTH);
		
		gameFrame.add(clearingChitsPane, BorderLayout.EAST);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		newBoardComponent.updateModel(model);
	}
	
	private void updateGold(int amount) {
		goldPanel.setText("Gold: " + amount);
	}
	
	private void updateNotoriety(int amount) {
		notorietyPanel.setText("Notoriety: " + amount);
	}
	
	private void updateFame(int amount) {
		famePanel.setText("Fame: " + amount);
	}
	
	/**
	 * Let the mediator update all elements when the character is updated.
	 * @param character
	 */
	private void updateCharacter(MRCharacter character) {
		updateGold(character.getGold());
		updateNotoriety(character.getNotoriety());
		updateFame(character.getFame());
	}
	
}
