package com.magicrealm.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.core.CombinableMatcher.CombinableEitherMatcher;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.models.armors.Armor;
import com.magicrealm.models.armors.Armor.Protection;
import com.magicrealm.models.armors.Armor.Slot;
import com.magicrealm.models.armors.Armor.State;
import com.magicrealm.models.weapons.Weapon;
import com.magicrealm.networking.IClientService;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.server.state.DaylightState;

public class Combat {
	
	private final Log log = LogFactory.getLog(Combat.class);
	
	List<Combatant> combatants = new ArrayList<Combatant>();
	List<Combatant> dead = new ArrayList<Combatant>();
	ServerGameState gameState;
	
	public Combat(ServerGameState gameState, List<MRCharacter> characters) {
		this.gameState = gameState;
		for(MRCharacter c: characters) {
			combatants.add(new Combatant(c));
		}
		multiRound();
	}
	
	/**
	 * A loop that iterates over all rounds of combat
	 */
	public void multiRound() {
		while(true) {
			setupRound();
			setAllAttackers();
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
//			fatigue();
			showHealth();
		}
	}
	
	/**
	 * handles targeting and random assignment of remaining monsters 
	 */
	public void encounter() {
		
		// choose target
		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
			Combatant[] attackers = c.getAttackers().toArray(new Combatant[0]);
			Object obj = client.clientSelect(attackers, "Choose a target");
			if(obj != null)
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
			if(c.getTarget() == null) {
				continue;
			}
			IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
			c.setFightChit((ActionChit) client.clientSelect(c.getCharacter().getFightChits().toArray(new ActionChit[0]), "Choose a fight chit"));
			c.setAttackDirection((Protection) client.clientSelect(Protection.values(), "Choose an attack"));
			c.setMoveChit((ActionChit) client.clientSelect(c.getCharacter().getMoveChits().toArray(new ActionChit[0]), "Choose a move chit"));
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
			if(c.getFightChit() != null && c.getMoveChit() != null) {
				IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
				ActionChit ac = (ActionChit) client.clientSelect(new ActionChit[]{c.getFightChit(), c.getMoveChit()}, "Choose a chit to fatigue");
				c.getCharacter().fatigueChit(ac);
			}
		}
	}
	
	public void setupRound() {
		for(Combatant c: combatants) {
			c.setTarget(null);
			c.setFightChit(null);
			c.setMoveChit(null);
			c.setDefenseDirection(null);
			c.setAttackDirection(null);
			c.setAttackers(new ArrayList<Combatant>());
		}
	}
	
	public void attack(Combatant c) {
		Combatant target = c.getTarget();
		MRCharacter targetCharacter = target.getCharacter();
		
		if(target == null) {
			return;
		}
		
		IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
		Weight harm = c.getTotalHarm(false);
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
			harm = c.getTotalHarm(true);
		}
		
		if(harm != Weight.NEGLIGIBLE) {
			ActionChit chit = (ActionChit) client.clientSelect(targetCharacter.getNonWoundedActionChits().toArray(new ActionChit[0]), "Select an action chit to wound");
			targetCharacter.fatigueChit(chit);
			targetCharacter.decreaseHealth(1);
			if(targetCharacter.getHealth() == 0) {
				dead.add(target);
			}
			client.sendMessage("You harmed "+target.getCharacter().getName());
		}else if(harm.compareTo(target.getCharacter().getVulnerability()) > 0) {
			// TODO add following:
			// handle dead character
			// drop belongings
			client.sendMessage("You killed "+target.getCharacter().getName());
			dead.add(target);
		} else {
			client.sendMessage("Your attack did no harm");
		}
	}
	
	public void setAllAttackers() {
		for(Combatant c: combatants) {
			c.setAttackers(getAttackers(c));
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
		MRCharacter character = attacker.getCharacter();
		for(Combatant c: combatants) {
			if((character.isHiddenEnemiesFound() || !c.getCharacter().isHidden()) && c != attacker)
				newList.add(c);
		}
		if(attacker.getCharacter().isHidden())
			newList.add(null);
		return newList;
	}
	
	public void combatEndedMessage() {
		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
			client.sendMessage("Combat in you clearing has ended");
		}
	}
	
	public void showHealth() {

		for(Combatant c: combatants) {
			IClientService client = gameState.getClientService(c.getCharacter().getCharacterType());
			client.sendMessage("You have "+c.getCharacter().getHealth()+" health");
		}
	}

}