package com.magicrealm.models.monsters;

public class Dragon extends MRMonster {
	
	public Dragon() {
		super(monster.dragon);
	}
	
	@Override
	public String getImageName() {
		return "dragon";
	}
	
}
