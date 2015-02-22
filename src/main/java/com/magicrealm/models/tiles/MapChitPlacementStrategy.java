package com.magicrealm.models.tiles;

import java.util.ArrayList;
import java.util.List;

import com.magicrealm.models.SoundChit;
import com.magicrealm.models.WarningChit;
import com.magicrealm.models.SoundChit.sound;
import com.magicrealm.models.WarningChit.warning;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.chits.MapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.SiteChit.site;

public abstract class MapChitPlacementStrategy {
	
	private MagicRealmHexEngineModel model;

	public MapChitPlacementStrategy(MagicRealmHexEngineModel model) {
		this.setModel(model);
	}
	
	public abstract void placeMapChits();
	
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
		return new SiteChit(site.lost_city);
	}
	
	public static SiteChit getLostCastleChit() {
		return new SiteChit(site.lost_castle);
	}
	
	public static List<MapChit> getSiteChits() {
		site[] s = new site[] { site.altair, site.cairns, site.hoard,
				site.lair, site.pool, site.shrine, site.statue, site.vault };
		
		ArrayList<MapChit> list = new ArrayList<MapChit>();
		for (site site2 : s) {
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

	public MagicRealmHexEngineModel getModel() {
		return model;
	}

	public void setModel(MagicRealmHexEngineModel model) {
		this.model = model;
	}
}
