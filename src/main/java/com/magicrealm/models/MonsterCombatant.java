package com.magicrealm.models;

import com.magicrealm.models.monsters.MRMonster;

public class MonsterCombatant extends Combatant {
	
	private MRMonster.monster monster;
	
	public MonsterCombatant(MRMonster monster) {
		this.monster = monster.getMonsterType();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		MonsterCombatant charObj;
		if(obj instanceof MonsterCombatant) {
			charObj = (MonsterCombatant) obj;
		} else
			return false;
		return monster.equals(charObj.getMonster());
	}

	public MRMonster.monster getMonster() {
		return monster;
	}

	public void setMonster(MRMonster.monster monster) {
		this.monster = monster;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return monster.name();
	}

}
