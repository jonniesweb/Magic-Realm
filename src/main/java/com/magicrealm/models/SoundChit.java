package com.magicrealm.models;

import com.magicrealm.models.chits.MapChit;

public class SoundChit extends MapChit {
	
	public enum sound {howl, flutter, roar, patter, slither};

	private sound soundType;

	public SoundChit(sound sound) {
		this.setSoundType(sound);
	}

	public sound getSoundType() {
		return soundType;
	}

	public void setSoundType(sound soundType) {
		this.soundType = soundType;
	}
	
	
}
