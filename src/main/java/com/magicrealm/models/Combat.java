package com.magicrealm.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Protection;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.armors.Armor.State;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class Combat {
	
	List<Combatant> combatants = new ArrayList<Combat.Combatant>();
	List<Combatant> dead = new ArrayList<Combat.Combatant>();
	ServerGameState gameState;
	
	public Combat(ServerGameState gameState, List<MRCharacter> characters) {
		for(MRCharacter c: characters) {
			combatants.add(new Combatant(c));
		}
		this.gameState = gameState;
		multiRound();
	}
	
	public void multiRound() {
		while(true) {
			setupRound();
			setAllAttackers();
			if(combatants.size() < 2) {
				break;
			} else {
				encounter();
				if(anyAttack()) {
					break;
				}
				melee();
				fatigue();
			}
		}
	}
	
	// targeting
	// luring
	// random assignment of remaining monsters
	public void encounter() {
		
		// choose target
		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.character.getCharacterType());
			c.setTarget((Combatant) client.clientSelect(c.attackers.toArray(new MRCharacter[0]), "Choose a target"));
		}
	}
	
	public void melee() {
		
		// choose attack
		for(Combatant c: combatants) {
			if(c.target == null) {
				continue;
			}
			IClientService client = gameState.getClientService(c.character.getCharacterType());
			c.fightChit = (ActionChit) client.clientSelect(c.character.getFightChits().toArray(new ActionChit[0]), "Choose a fight chit");
			c.attackDirection = (Protection) client.clientSelect(Protection.values(), "Choose an attack");
			c.moveChit = (ActionChit) client.clientSelect(c.character.getMoveChits().toArray(new ActionChit[0]), "Choose a move chit");
			c.defenseDirection = (Protection) client.clientSelect(Protection.values(), "Choose a defense");
		}
		
		dead.clear();
		
		// attack
		Iterator<Combatant> it = combatants.iterator();
		while(it.hasNext()) {
			Combatant c = (Combatant) it.next();
			if(dead.contains(c)) {
				it.remove();
			} else {
				c.attack();				
			}
		}
	}
	
	public void fatigue() {
		// fatigue 1 of the 2 chits
		for(Combatant c: combatants) {
			if(c.fightChit != null && c.moveChit != null) {
				IClientService client = gameState.getClientService(c.character.getCharacterType());
				ActionChit ac = (ActionChit) client.clientSelect(new ActionChit[]{c.fightChit, c.moveChit}, "Choose a chit to fatigue");
				c.character.getActionChits().get(c.character.getActionChits().indexOf(ac));
			}
		}
	}
	
	public void setupRound() {
		for(Combatant c: combatants) {
			c.target = null;
			c.fightChit = null;
			c.moveChit = null;
			c.defenseDirection = null;
			c.attackDirection = null;
			c.attackers.clear();
		}
	}
	
	public void setAllAttackers() {
		for(Combatant c: combatants) {
			c.setAttackers(getAttackers(c));
		}
	}
	
	public boolean anyAttack() {
		boolean b = false;
		for(Combatant c: combatants) {
			if(c.target != null) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	public List<Combatant> getAttackers(Combatant attacker) {
		List<Combatant> newList = new ArrayList<Combatant>();
		MRCharacter character = attacker.character;
		for(Combatant c: combatants) {
			if((character.isFoundHidden() || !c.character.isHidden()) && c != attacker)
				newList.add(c);
		}
		if(attacker.character.isHidden())
			newList.add(null);
		return newList;
	}
	
	protected class Combatant {
		MRCharacter character;
		List<Combatant> attackers;
		Combatant target;
		ActionChit fightChit;
		Protection attackDirection;
		ActionChit moveChit;
		Protection defenseDirection;
		
		public Combatant(MRCharacter character) {
			this.character = character;
		}
		
		public void setTarget(Combatant target) {
			this.target = target;
		}
		
		public void setAttackers(List<Combatant> attackers) {
			this.attackers = attackers;
		}
		
		public void attack() {
			if(target == null) {
				return;
			}
			IClientService client = gameState.getClientService(character.getCharacterType());
			Weight harm = getTotalHarm();
			Armor blockingArmor;
			if(attackDirection == target.defenseDirection) {
				blockingArmor = target.character.getActiveArmor(Slot.SHIELD);
			} else if(attackDirection == Protection.SWING || attackDirection == Protection.THRUST) {
				blockingArmor = target.character.getActiveArmor(Slot.BREASTPLATE);
			} else if(attackDirection == Protection.SMASH) {
				blockingArmor = target.character.getActiveArmor(Slot.HELMET);
			} else {
				blockingArmor = target.character.getActiveArmor(Slot.SUIT);
			}
			
			if(blockingArmor != null) {
				int r = harm.compareTo(blockingArmor.getWeight());
				if(r > 0) {
					target.character.getArmors().remove(blockingArmor);
				} else if(r == 0) {
					if(blockingArmor.getState() == State.DAMAGED) {
						target.character.getArmors().remove(blockingArmor);
					} else {
						blockingArmor.damaged();
					}
				}
				harm = harm.decrement(1);
				if(harm != Weight.NEGLIGIBLE) {
					ActionChit chit = (ActionChit) client.clientSelect(character.getActionChits().toArray(new ActionChit[0]), "Select an action chit to wound");
					chit.fatigue();
				}
			} else if(harm.compareTo(target.character.getVulnerability()) > 0) {
				// TODO add following:
				// handle dead character
				// drop belongings
				dead.add(target);
			}
		}
		
		public Weight getTotalHarm() {
			Weapon wep = character.getActiveWeapon();
			Weight harm = wep.getInflictedHarm();
			if(fightChit.getStrength().compareTo(wep.getHarm()) > 0)
				harm.increment(1);
			return harm;
		}
	}

}
