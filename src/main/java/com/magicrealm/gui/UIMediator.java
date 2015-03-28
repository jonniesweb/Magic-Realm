package com.magicrealm.gui;

import javax.swing.SwingUtilities;

import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class UIMediator {
	
	private BoardView boardView;
	private ClearingChitsPane clearingChitsPane;

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
	
}
