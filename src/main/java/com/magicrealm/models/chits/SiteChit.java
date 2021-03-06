package com.magicrealm.models.chits;

import java.util.ArrayList;
import java.util.List;

import com.magicrealm.models.treasure.TreasureChit;

public class SiteChit extends ClearingMapChit {
	
	public enum Site {
		altair, cairns, hoard, lair, pool, shrine, statue, vault, lost_castle, lost_city
	}
	
	private Site siteType;
	private List<TreasureChit> treasures = new ArrayList<TreasureChit>();
	
	/**
	 * Chits for lost castle or lost city can be stored in here
	 */
	private List<MapChit> extraChits;
	
	public SiteChit(Site s) {
		super(s.toString());
		this.setSiteType(s);
		if (s == Site.lost_castle || s == Site.lost_city) {
			extraChits = new ArrayList<>();
		}
	}
	
	@Override
	public int getClearing() {
		switch (siteType) {
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
			return 0;
			// not sure what to do here for lost city and lost castle
		}
	}
	
	public Site getSiteType() {
		return siteType;
	}
	
	public void setSiteType(Site siteType) {
		this.siteType = siteType;
	}

	public List<MapChit> getExtraChits() {
		return extraChits;
	}

	public void setExtraChits(List<MapChit> extraChits) {
		this.extraChits = extraChits;
	}

	@Override
	public String toString() {
		if (siteType == Site.lost_castle || siteType == Site.lost_city) {
			StringBuilder sb  = new StringBuilder("SiteChit: " + siteType + " contains: ");
			for (MapChit mapChit : extraChits) {
				sb.append(mapChit);
				sb.append(" ");
			}
			return sb.toString();
		}
		return "SiteChit: " + siteType + " clearing: " + getClearing();
	}

	public List<TreasureChit> getTreasures() {
		return treasures;
	}

	public void addTreasure(TreasureChit chit) {
		getTreasures().add(chit);
	}
}
