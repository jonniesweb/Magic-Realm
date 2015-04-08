package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.List;

import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.SiteChit.Site;
import com.magicrealm.models.chits.SoundChit;
import com.magicrealm.models.chits.SoundChit.sound;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.chits.WarningChit.warning;

/**
 * A strategy for placing all of the map chits on a board (Site, sound and
 * warning chits)
 */
public abstract class MapChitPlacementStrategy {
	
	public MapChitPlacementStrategy() {
	}
	
	/**
	 * Place the map chits using the given strategy
	 * @param model
	 */
	public abstract void placeMapChits(MagicRealmHexEngineModel model);
	
	public static List<WarningChit> getCWarningChits() {
		return getWarningChits();
	}
	
	private static ArrayList<WarningChit> getWarningChits() {
		ArrayList<WarningChit> list = new ArrayList<WarningChit>();
		for (warning warn : warning.values()) {
			list.add(new WarningChit(warn));
		}
		return list;
	}
	
	public static List<WarningChit> getMWarningChits() {
		return getWarningChits();
	}
	
	public static List<WarningChit> getVWarningChits() {
		return getWarningChits();
	}
	
	public static List<WarningChit> getWWarningChits() {
		return getWarningChits();
	}
	
	public static SiteChit getLostCityChit() {
		return new SiteChit(Site.lost_city);
	}
	
	public static SiteChit getLostCastleChit() {
		return new SiteChit(Site.lost_castle);
	}
	
	public static List<MapChit> getSiteChits() {
		Site[] s = new Site[] { Site.altair, Site.cairns, Site.hoard,
				Site.lair, Site.pool, Site.shrine, Site.statue, Site.vault };
		
		ArrayList<MapChit> list = new ArrayList<MapChit>();
		for (Site site2 : s) {
			list.add(new SiteChit(site2));
		}
		
		return list;
	}
	
	public static List<SoundChit> getSoundChits() {
		sound[] sounds = sound.values();
		
		ArrayList<SoundChit> list = new ArrayList<SoundChit>();
		for (sound sound : sounds) {
			list.add(new SoundChit(sound));
		}
		
		return list;
	}
}
