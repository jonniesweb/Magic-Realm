package com.magicrealm.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class UIMediator {
	
	private ClientGameState gameState = ClientGameState.getInstance();
	private BoardComponent boardView;
	private ClearingChitsPane clearingChitsPane;
	
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
		boardView = new BoardComponent(ClientGameState.getInstance(), model);
		clearingChitsPane = new ClearingChitsPane();
	}
	
	public void widgetChanged() {
		
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		boardView.updateModel(model);
	}
	
}
