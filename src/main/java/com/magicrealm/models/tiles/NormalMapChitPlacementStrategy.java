package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.GameTile.TileType;

/**
 * Places Map Chits on the board according to the rulebook
 */
public class NormalMapChitPlacementStrategy extends MapChitPlacementStrategy {
	
	private static final Log log = LogFactory.getLog(NormalMapChitPlacementStrategy.class);
	
	public NormalMapChitPlacementStrategy() {
	}

	@Override
	public void placeMapChits(MagicRealmHexEngineModel model) {

		GameTile[] valleys = new GameTile[] { model.getTile(TileType.AV),
				model.getTile(TileType.BV), model.getTile(TileType.CV), model.getTile(TileType.DV),
				model.getTile(TileType.EV) };
		
		GameTile[] woods = new GameTile[] { model.getTile(TileType.LW), model.getTile(TileType.MW),
				model.getTile(TileType.NW), model.getTile(TileType.OW), model.getTile(TileType.PW) };
		
		GameTile[] caves = new GameTile[] { model.getTile(TileType.B), model.getTile(TileType.CN),
				model.getTile(TileType.CS), model.getTile(TileType.HP), model.getTile(TileType.R) };
		
		GameTile[] mountains = new GameTile[] { model.getTile(TileType.CF),
				model.getTile(TileType.CG), model.getTile(TileType.DW), model.getTile(TileType.L),
				model.getTile(TileType.M) };
		
		// add all warning chits
		addChitsToTiles(valleys, getVWarningChits());
		addChitsToTiles(woods, getWWarningChits());
		addChitsToTiles(caves, getCWarningChits());
		addChitsToTiles(mountains, getMWarningChits());
		
		/*
		 * Add site and sound chits
		 */
		
		// combine all sights and sounds
		List<MapChit> mapChits = getSiteChits();
		mapChits.addAll(getSoundChits());
		mapChits.addAll(getSoundChits()); // add it twice
		Collections.shuffle(mapChits);
		
		// add cave site/sound chits
		ArrayList<MapChit> caveSoundSiteChits = new ArrayList<>(mapChits.subList(0, 4));

		// add lost city to cave and 5 site/sound chits
		SiteChit lostCityChit = getLostCityChit();
		lostCityChit.getExtraChits().addAll(mapChits.subList(8, 13));
		log.debug("adding chits to lost city: " + lostCityChit.getExtraChits());
		caveSoundSiteChits.add(lostCityChit);
		addChitsToTiles(caves, caveSoundSiteChits);

		// add mountain site/sound chits
		ArrayList<MapChit> mountainSoundSiteChits = new ArrayList<>(mapChits.subList(4, 8));

		// add lost castle to mountain and 5 site/sound chits
		SiteChit lostCastleChit = getLostCastleChit();
		lostCastleChit.getExtraChits().addAll(mapChits.subList(13, 18));
		log.debug("adding chits to lost castle: " + lostCastleChit.getExtraChits());
		mountainSoundSiteChits.add(lostCastleChit);
		addChitsToTiles(mountains, mountainSoundSiteChits);
		
	}
	
	/**
	 * Helper method for randomly adding chits to the tiles
	 * @param tiles
	 * @param chits
	 */
	private void addChitsToTiles(GameTile[] tiles, List<? extends MapChit> chits) {
		Collections.shuffle(chits);
		for (int i = 0; i < tiles.length; i++) {
			if (chits.get(i) instanceof WarningChit) {
				tiles[i].setWarningChit((WarningChit) chits.get(i));
			} else {
				tiles[i].setSiteSoundChit((ClearingMapChit) chits.get(i));
			}
			
			log.debug("Adding " + chits.get(i) + " to " + tiles[i]);
		}
	}
	
}
