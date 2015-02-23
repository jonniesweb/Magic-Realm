package com.magicrealm.models.chits;


public class SoundChit extends MapChit {
	
	public enum sound {howl, flutter, roar, patter, slither};

	private sound soundType;

	public SoundChit(sound sound) {
		super(sound.toString());
		this.setSoundType(sound);
	}

	public sound getSoundType() {
		return soundType;
	}

	public void setSoundType(sound soundType) {
		this.soundType = soundType;
	}
	
	@Override
	public String toString() {
		return "SoundChit: " + soundType;
	}
}
