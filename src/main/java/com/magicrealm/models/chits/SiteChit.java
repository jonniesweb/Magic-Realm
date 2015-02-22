package com.magicrealm.models.chits;

import java.util.ArrayList;
import java.util.List;

public class SiteChit extends MapChit {
	
	public enum site {
		altair, cairns, hoard, lair, pool, shrine, statue, vault, lost_castle, lost_city
	}
	
	private site siteType;
//	private List<Treasure> treasures;
	
	/**
	 * Chits for lost castle or lost city can be stored in here
	 */
	private List<MapChit> extraChits = new ArrayList<MapChit>();
	
	public SiteChit(site site) {
		this.setSiteType(site);
	}
	
	public static int getClearing(site site) {
		switch (site) {
		case altair:
			return 1;
		case cairns:
			return 5;
		case hoard:
			return 6;
		case lair:
			return 3;
		case pool:
			return 6;
		case shrine:
			return 4;
		case statue:
			return 2;
		case vault:
			return 3;
		default:
			throw new RuntimeException("no clearing avaliable");
		}
	}
	
	public site getSiteType() {
		return siteType;
	}
	
	public void setSiteType(site siteType) {
		this.siteType = siteType;
	}

	public List<MapChit> getExtraChits() {
		return extraChits;
	}

	public void setExtraChits(List<MapChit> extraChits) {
		this.extraChits = extraChits;
	}
	
}
