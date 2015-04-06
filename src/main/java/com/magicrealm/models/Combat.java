package com.magicrealm.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Protection;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.armors.Armor.State;
import com.magicrealm.models.monsters.MRMonster;
import com.magicrealm.models.monsters.MRMonster.monster;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class Combat {
	
	private final Log log = LogFactory.getLog(Combat.class);
	
	List<CharacterCombatant> characterCombatants = new ArrayList<CharacterCombatant>();
	List<MonsterCombatant> monsterCombatants = new ArrayList<MonsterCombatant>();
	Map<CharacterType, MRCharacter> characters;
	List<Combatant> dead = new ArrayList<Combatant>();
	Map<monster, MRMonster> monsters;
	ServerGameState gameState;
	
	public Combat(ServerGameState gameState, List<MRCharacter> characters, List<MRMonster> monsters) {
		this.gameState = gameState;
		this.characters = new HashMap<>();
		this.monsters = new HashMap<>();
		
		for(MRCharacter c: characters) {
			characterCombatants.add(new CharacterCombatant(c));
			this.characters.put(c.getCharacterType(), c);
		}
		for(MRMonster c: monsters) {
			monsterCombatants.add(new MonsterCombatant(c));
			this.monsters.put(c.getMonsterType(), c);
		}
		
		multiRound();
	}
	
	/**
	 * A loop that iterates over all rounds of combat
	 */
	public void multiRound() {
		while(true) {
			setupRound();
			if(characterCombatants.size() > 0 && characterCombatants.size() + monsterCombatants.size() < 2) {
				combatEndedMessage();
				break;
			}
			encounter();
			if(!isThereTarget()) {
				combatEndedMessage();
				break;
			}
			melee();
			fatigue();
			showHealth();
		}
	}
	
	/**
	 * handles targeting, running away and random assignment of remaining monsters 
	 */
	public void encounter() {
		
		CharacterCombatant combatant;
		Iterator<CharacterCombatant> it = characterCombatants.iterator();

		while(it.hasNext()) {
			combatant = (CharacterCombatant) it.next();
			IClientService client = gameState.getClientService(combatant.getCharacter());
			MRCharacter character = characters.get(combatant.getCharacter());
			ActionChit[] chits = character.getNonWoundedMoveChits().toArray(new ActionChit[0]);
			Object obj = client.clientSelect(chits, "Run away", JOptionPane.YES_NO_OPTION);
			if(obj instanceof ActionChit) {
				character.fatigueChit((ActionChit) obj);
				it.remove();
				client.sendMessage("You ran away");
				// TODO reduce activities for this character by 1 next turn
			}
		}
		
		if(characterCombatants.size() < 1 && characterCombatants.size() + monsterCombatants.size() < 2) {
			return;
		}
		
		// choose target
		for(CharacterCombatant c: characterCombatants) {
			IClientService client = gameState.getClientService(c.getCharacter());
			Combatant[] attackers = getAttackers(c).toArray(new Combatant[0]);
			Object obj = client.clientSelect(attackers, "Choose a target");
			if(obj instanceof MonsterCombatant) {
				// monsters retaliate if attacked
				monsterCombatants.get(monsterCombatants.indexOf(obj)).setTarget(c);
			}
			c.setTarget((Combatant) obj);
		}
		
		// assign targets for remaining monsters
		for(MonsterCombatant c: monsterCombatants) {
			if(c.getTarget() == null) {
				Random r = new Random();
				int i = r.nextInt(characterCombatants.size());
				c.setTarget(characterCombatants.get(i));
			}
		}
	}
	
	/**
	 * Handles choosing attack options and
	 * executing attacks on targets
	 */
	public void melee() {
		
		// choose attack
		for(CharacterCombatant c: characterCombatants) {
			MRCharacter character = characters.get(c.getCharacter());
			if(c.getTarget() == null) {
				continue;
			}
			IClientService client = gameState.getClientService(c.getCharacter());
			
			ArrayList<ActionChit> nwFightChits = character.getNonWoundedFightChits();
			ArrayList<ActionChit> nwMoveChits = character.getNonWoundedMoveChits();
			
			c.setMainActionChit((ActionChit) client.clientSelect(nwFightChits.toArray(new ActionChit[0]), "Choose a fight chit"));
			c.setAttackDirection((Protection) client.clientSelect(Protection.values(), "Choose an attack"));
			c.setDefenseChit((ActionChit) client.clientSelect(nwMoveChits.toArray(new ActionChit[0]), "Choose a move chit"));
			c.setDefenseDirection((Protection) client.clientSelect(Protection.values(), "Choose a defense"));
		}
		
		Protection[] values = Protection.values();
		
		for(MonsterCombatant c: monsterCombatants) {
			Random r = new Random();
			int i = r.nextInt(values.length);
			c.setAttackDirection(values[i]);
		}
		
		dead.clear();
		
		// carry out attacks from characters
		Iterator<CharacterCombatant> it = characterCombatants.iterator();
		while(it.hasNext()) {
			CharacterCombatant c = (CharacterCombatant) it.next();
			if(dead.contains(c)) {
				it.remove();
			} else {
				attack(c);				
			}
		}
		
		// carry out attacks from monsters
		Iterator<MonsterCombatant> it1 = monsterCombatants.iterator();
		while(it1.hasNext()) {
			MonsterCombatant c = it1.next();
			if(dead.contains(c)) {
				it1.remove();
			} else {
				attack(c);
			}
		}
		
		for(Combatant c: dead){
			if(c instanceof CharacterCombatant) {				
				characterCombatants.remove(c);
			} else if(c instanceof MonsterCombatant) {
				monsterCombatants.remove(c);
			}
		}
	}
	
	public void fatigue() {
		// fatigue 1 of the 2 chits
		for(CharacterCombatant c: characterCombatants) {
			MRCharacter character = characters.get(c.getCharacter());
			if(c.getMainActionChit() != null && c.getDefenseChit() != null) {
				IClientService client = gameState.getClientService(character.getCharacterType());
				ActionChit ac = (ActionChit) client.clientSelect(new ActionChit[]{c.getMainActionChit(), c.getDefenseChit()}, "Choose a chit to fatigue");
				character.fatigueChit(ac);
			}
		}
	}
	
	public void setupRound() {
		for(Combatant c: characterCombatants) {
			c.setTarget(null);
			c.setMainActionChit(null);
			c.setDefenseChit(null);
			c.setDefenseDirection(null);
			c.setAttackDirection(null);
		}
		for(Combatant c: monsterCombatants) {
			c.setMainActionChit(null);
			c.setDefenseChit(null);
			c.setDefenseDirection(null);
			c.setAttackDirection(null);
		}
	}
	
	@SuppressWarnings("unused")
	public void attack(CharacterCombatant c) {
		Combatant target = c.getTarget();
		MRCharacter targetCharacter = null;
		MRMonster targetMonster = null;
		
		if(target instanceof CharacterCombatant) {			
			targetCharacter = characters.get(((CharacterCombatant) target).getCharacter());
		} else if(target instanceof MonsterCombatant) {
			targetMonster = monsters.get(((MonsterCombatant) target).getMonster());
		}
		
		IClientService client = gameState.getClientService(c.getCharacter());
		Weight harm = getTotalHarm(c, false);
		Armor blockingArmor = null;
		if(targetCharacter != null) {
			if(c.getAttackDirection() == target.getDefenseDirection()) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.SHIELD);
			} else if(c.getAttackDirection() == Protection.SWING || c.getAttackDirection() == Protection.THRUST) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.BREASTPLATE);
			} else if(c.getAttackDirection() == Protection.SMASH) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.HELMET);
			} else {
				blockingArmor = targetCharacter.getActiveArmor(Slot.SUIT);
			}
		}
		
		if(blockingArmor != null) {
			int r = harm.compareTo(blockingArmor.getWeight());
			if(r > 0) {
				targetCharacter.getArmors().remove(blockingArmor);
			} else if(r == 0) {
				if(blockingArmor.getState() == State.DAMAGED) {
					targetCharacter.getArmors().remove(blockingArmor);
				} else {
					blockingArmor.damaged();
				}
			}
			harm = getTotalHarm(c, false);
		}
		
		if(targetCharacter != null) {
			if(blockingArmor == null && harm.compareTo(targetCharacter.getVulnerability()) > 0) {
				// TODO add following:
				// handle dead character
				// drop belongings
				client.sendMessage("You killed "+targetCharacter.getName());
				dead.add(target);
			} else if(harm != Weight.NEGLIGIBLE) {
				client.sendMessage("You harmed "+targetCharacter.getName());
				ActionChit chit = (ActionChit) client.clientSelect(targetCharacter.getNonWoundedActionChits().toArray(new ActionChit[0]), "Select an action chit to wound");
				targetCharacter.fatigueChit(chit);
				targetCharacter.decreaseHealth(1);
				if(targetCharacter.getHealth() == 0) {
					died(target);
					client.sendMessage("You killed "+targetCharacter.getName());
				}
			} else {
				client.sendMessage("Your attack did no harm");
			}
		} else if(targetMonster != null) {
			if(harm.compareTo(targetMonster.getVulnerability()) >= 0) {
				died(target);
				client.sendMessage("You killed "+targetMonster.getName());
			} else {
				client.sendMessage("Your attack did no harm");
			}
		}
	}
	
	public void attack(MonsterCombatant c) {
		Combatant target = c.getTarget();
		MRCharacter targetCharacter = characters.get(((CharacterCombatant) target).getCharacter());
		
		IClientService client = gameState.getClientService(targetCharacter.getCharacterType());
		
		Weight harm = getTotalHarm(c, false);
		Armor blockingArmor = null;
		if(targetCharacter != null) {
			if(c.getAttackDirection() == target.getDefenseDirection()) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.SHIELD);
			} else if(c.getAttackDirection() == Protection.SWING || c.getAttackDirection() == Protection.THRUST) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.BREASTPLATE);
			} else if(c.getAttackDirection() == Protection.SMASH) {
				blockingArmor = targetCharacter.getActiveArmor(Slot.HELMET);
			} else {
				blockingArmor = targetCharacter.getActiveArmor(Slot.SUIT);
			}
		}
		
		if(blockingArmor != null) {
			int r = harm.compareTo(blockingArmor.getWeight());
			if(r > 0) {
				targetCharacter.getArmors().remove(blockingArmor);
			} else if(r == 0) {
				if(blockingArmor.getState() == State.DAMAGED) {
					targetCharacter.getArmors().remove(blockingArmor);
				} else {
					blockingArmor.damaged();
				}
			}
			harm = getTotalHarm(c, false);
		}
		
		if(blockingArmor == null && harm.compareTo(targetCharacter.getVulnerability()) > 0) {
			died(target);
			client.sendMessage("You were killed by "+c.toString());
		} else if(harm != Weight.NEGLIGIBLE) {
			client.sendMessage("You were harmed by "+c.toString());
			ActionChit chit = (ActionChit) client.clientSelect(targetCharacter.getNonWoundedActionChits().toArray(new ActionChit[0]), "Select an action chit to wound");
			targetCharacter.fatigueChit(chit);
			targetCharacter.decreaseHealth(1);
			if(targetCharacter.getHealth() == 0) {
				died(target);
				client.sendMessage("You were killed by "+c.toString());
			}
		} else {
			client.sendMessage(c.toString()+" attacked you but did no harm");
		}
	}
	
	public void died(Combatant target) {
		// TODO add following:
		// handle dead character
		// drop belongings
		dead.add(target);
	}
	
	public boolean isThereTarget() {
		boolean b = false;
		for(Combatant c: characterCombatants) {
			if(c.getTarget() != null) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	public List<Combatant> getAttackers(CharacterCombatant attacker) {
		List<Combatant> newList = new ArrayList<Combatant>();
		MRCharacter attackerCharacter = characters.get(attacker.getCharacter());
		for(CharacterCombatant c: characterCombatants) {
			MRCharacter character = characters.get(c.getCharacter());
			if((attackerCharacter.isHiddenEnemiesFound() || !character.isHidden()) && c != attacker)
				newList.add(c);
		}
		for(MonsterCombatant c: monsterCombatants) {
			newList.add(c);
		}

		return newList;
	}
	
	public void combatEndedMessage() {
		for(CharacterCombatant c: characterCombatants) {
			MRCharacter character = characters.get(c.getCharacter());
			IClientService client = gameState.getClientService(character.getCharacterType());
			client.sendMessage("Combat in you clearing has ended");
		}
	}
	
	public void showHealth() {

		for(CharacterCombatant c: characterCombatants) {
			MRCharacter character = characters.get(c.getCharacter());
			IClientService client = gameState.getClientService(character.getCharacterType());
			client.sendMessage("You have "+character.getHealth()+" health");
		}
	}
	
	public Weight getTotalHarm(Combatant character, boolean hitsArmor) {
		
		Weapon wep = null;
		Weight harm;
		
		if(character instanceof CharacterCombatant) {
			wep = characters.get(((CharacterCombatant) character).getCharacter()).getActiveWeapon();
		} else if(character instanceof MonsterCombatant) {
			wep = monsters.get(((MonsterCombatant) character).getMonster()).getActiveWeapon();
		}
		
		if(hitsArmor)
			harm = wep.getInflictedHarmThroughArmor();
		else
			harm = wep.getInflictedHarm();
		
		if(character.getMainActionChit() != null && character.getMainActionChit().getStrength().compareTo(wep.getHarm()) > 0)
			harm.increment(1);
		return harm;
	}

}
