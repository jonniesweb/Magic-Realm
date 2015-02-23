package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.tiles.GameTile.TileType;

/**
 * Places Map Chits on the board according to the rulebook
 */
public class NormalMapChitPlacementStrategy extends MapChitPlacementStrategy {
	
	private static final Log log = LogFactory.getLog(NormalMapChitPlacementStrategy.class);
	
	public NormalMapChitPlacementStrategy(MagicRealmHexEngineModel model) {
		super(model);
	}

	@Override
	public void placeMapChits() {

		GameTile[] valleys = new GameTile[] { get(TileType.AV),
				get(TileType.BV), get(TileType.CV), get(TileType.DV),
				get(TileType.EV) };
		
		GameTile[] woods = new GameTile[] { get(TileType.LW), get(TileType.MW),
				get(TileType.NW), get(TileType.OW), get(TileType.PW) };
		
		GameTile[] caves = new GameTile[] { get(TileType.B), get(TileType.CN),
				get(TileType.CS), get(TileType.HP), get(TileType.R) };
		
		GameTile[] mountains = new GameTile[] { get(TileType.CF),
				get(TileType.CG), get(TileType.DW), get(TileType.L),
				get(TileType.M) };
		
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
				tiles[i].setSiteSoundChit(chits.get(i));
			}
			
			log.debug("Adding " + chits.get(i) + " to " + tiles[i]);
		}
	}
	
	private GameTile get(TileType type) {
		return getModel().getTile(type);
	}
	
}
