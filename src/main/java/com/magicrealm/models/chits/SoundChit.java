package com.magicrealm.models.chits;


public class SoundChit extends ClearingMapChit {
	
	public enum sound {
		howl4, howl5, flutter1, flutter2, roar4, roar6, patter2, patter5, slither3, slither6
	}
	
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
		return "SoundChit: " + soundType + " clearing: " + getClearing();
	}

	@Override
	public int getClearing() {
		switch (soundType) {
		case flutter1:
			return 1;
		case flutter2:
			return 2;
		case howl4:
			return 4;
		case howl5:
			return 5;
		case patter2:
			return 2;
		case patter5:
			return 5;
		case roar4:
			return 4;
		case roar6:
			return 6;
		case slither3:
			return 3;
		case slither6:
			return 6;
		default:
			throw new RuntimeException("invalid sound");
		}
	}
}
