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
	private GoldPanel goldPanel;
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
				updateModel(model);
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
		goldPanel = new GoldPanel();
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
		gameFrame.add(topPanel, BorderLayout.NORTH);
		
		gameFrame.add(consoleLog, BorderLayout.SOUTH);
		
		gameFrame.add(clearingChitsPane, BorderLayout.EAST);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.repaint();
	}
	
	public void widgetChanged() {
		
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		newBoardComponent.updateModel(model);
	}
	
	public void updateGold(int goldAmount) {
		goldPanel.updateGold(goldAmount);
	}
	
}
