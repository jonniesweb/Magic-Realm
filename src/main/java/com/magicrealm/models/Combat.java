package com.magicrealm.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Protection;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.armors.Armor.State;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;

public class Combat {
	
	private final Log log = LogFactory.getLog(Combat.class);
	
	List<Combatant> combatants = new ArrayList<Combatant>();
	Map<CharacterType, MRCharacter> characters;
	List<Combatant> dead = new ArrayList<Combatant>();
	ServerGameState gameState;
	
	public Combat(ServerGameState gameState, List<MRCharacter> characters) {
		this.gameState = gameState;
		this.characters = new HashMap<>();
		for(MRCharacter c: characters) {
			combatants.add(new Combatant(c));
			this.characters.put(c.getCharacterType(), c);
		}
		multiRound();
	}
	
	/**
	 * A loop that iterates over all rounds of combat
	 */
	public void multiRound() {
		while(true) {
			setupRound();
			if(combatants.size() < 2) {
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
		
		Combatant combatant;
		Iterator<Combatant> it = combatants.iterator();

		while(it.hasNext()) {
			combatant = (Combatant) it.next();
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
		
		if(combatants.size() < 2) {
			return;
		}
		
		// choose target
		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.getCharacter());
			Combatant[] attackers = getAttackers(c).toArray(new Combatant[0]);
			Object obj = client.clientSelect(attackers, "Choose a target");
			if(obj instanceof Combatant)
				c.setTarget((Combatant) obj);
		}
	}
	
	/**
	 * Handles choosing attack options and
	 * executing attacks on targets
	 */
	public void melee() {
		
		// choose attack
		for(Combatant c: combatants) {
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
		
		dead.clear();
		
		// carry out attacks
		Iterator<Combatant> it = combatants.iterator();
		while(it.hasNext()) {
			Combatant c = (Combatant) it.next();
			if(dead.contains(c)) {
				it.remove();
			} else {
				attack(c);				
			}
		}
		
		for(Combatant c: dead){
			combatants.remove(c);
		}
	}
	
	public void fatigue() {
		// fatigue 1 of the 2 chits
		for(Combatant c: combatants) {
			MRCharacter character = characters.get(c.getCharacter());
			if(c.getMainActionChit() != null && c.getDefenseChit() != null) {
				IClientService client = gameState.getClientService(character.getCharacterType());
				ActionChit ac = (ActionChit) client.clientSelect(new ActionChit[]{c.getMainActionChit(), c.getDefenseChit()}, "Choose a chit to fatigue");
				character.fatigueChit(ac);
			}
		}
	}
	
	public void setupRound() {
		for(Combatant c: combatants) {
			c.setTarget(null);
			c.setMainActionChit(null);
			c.setDefenseChit(null);
			c.setDefenseDirection(null);
			c.setAttackDirection(null);
		}
	}
	
	public void attack(Combatant c) {
		Combatant target = c.getTarget();
		MRCharacter targetCharacter = characters.get(target.getCharacter());
		
		IClientService client = gameState.getClientService(c.getCharacter());
		Weight harm = getTotalHarm(c, false);
		Armor blockingArmor;
		if(c.getAttackDirection() == target.getDefenseDirection()) {
			blockingArmor = targetCharacter.getActiveArmor(Slot.SHIELD);
		} else if(c.getAttackDirection() == Protection.SWING || c.getAttackDirection() == Protection.THRUST) {
			blockingArmor = targetCharacter.getActiveArmor(Slot.BREASTPLATE);
		} else if(c.getAttackDirection() == Protection.SMASH) {
			blockingArmor = targetCharacter.getActiveArmor(Slot.HELMET);
		} else {
			blockingArmor = targetCharacter.getActiveArmor(Slot.SUIT);
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
		
		if(harm != Weight.NEGLIGIBLE) {
			client.sendMessage("You harmed "+targetCharacter.getName());
			ActionChit chit = (ActionChit) client.clientSelect(targetCharacter.getNonWoundedActionChits().toArray(new ActionChit[0]), "Select an action chit to wound");
			targetCharacter.fatigueChit(chit);
			targetCharacter.decreaseHealth(1);
			if(targetCharacter.getHealth() == 0) {
				client.sendMessage("You killed "+targetCharacter.getName());
				dead.add(target);
			}
		}else if(harm.compareTo(targetCharacter.getVulnerability()) > 0) {
			// TODO add following:
			// handle dead character
			// drop belongings
			client.sendMessage("You killed "+targetCharacter.getName());
			dead.add(target);
		} else {
			client.sendMessage("Your attack did no harm");
		}
	}
	
	public boolean isThereTarget() {
		boolean b = false;
		for(Combatant c: combatants) {
			if(c.getTarget() != null) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	public List<Combatant> getAttackers(Combatant attacker) {
		List<Combatant> newList = new ArrayList<Combatant>();
		MRCharacter attackerCharacter = characters.get(attacker.getCharacter());
		for(Combatant c: combatants) {
			MRCharacter character = characters.get(c.getCharacter());
			if((attackerCharacter.isHiddenEnemiesFound() || !character.isHidden()) && c != attacker)
				newList.add(c);
		}
		if(attackerCharacter.isHidden())
			newList.add(null);
		return newList;
	}
	
	public void combatEndedMessage() {
		for(Combatant c: combatants) {
			MRCharacter character = characters.get(c.getCharacter());
			IClientService client = gameState.getClientService(character.getCharacterType());
			client.sendMessage("Combat in you clearing has ended");
		}
	}
	
	public void showHealth() {

		for(Combatant c: combatants) {
			MRCharacter character = characters.get(c.getCharacter());
			IClientService client = gameState.getClientService(character.getCharacterType());
			client.sendMessage("You have "+character.getHealth()+" health");
		}
	}
	
	public Weight getTotalHarm(Combatant character, boolean hitsArmor) {
		
		Weapon wep = characters.get(character.getCharacter()).getActiveWeapon();
		Weight harm;
		
		if(hitsArmor)
			harm = wep.getInflictedHarmThroughArmor();
		else
			harm = wep.getInflictedHarm();
		
		if(character.getMainActionChit().getStrength().compareTo(wep.getHarm()) > 0)
			harm.increment(1);
		return harm;
	}

}
