package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.magicrealm.characters.MRCharacter;
import com.magicrealm.characters.MRCharacter.CharacterType;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.Dwelling.dwelling;

public class SelectCharacter extends JPanel {
	
	private ArrayList<CharacterButton> cButtons;
	private ButtonGroup group;
	private JPanel characterButtons;
	private dwelling startingLocation;
	
	public SelectCharacter() {
		setLayout(new BorderLayout());
		
		group = new ButtonGroup();
		
		characterButtons = new JPanel();
		characterButtons.setLayout(new GridLayout(1, 3));
		
		List<CharacterType> characters = ClientGameState.getInstance().getService().getAvaliableCharacters();
		
		CharacterButton cb;
		cButtons = new ArrayList<CharacterButton>();
		for(CharacterType c: characters) {
			cb = new CharacterButton(MRCharacter.createCharacter(c));
			cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SimpleSelection selectedLocation = new SimpleSelection(((CharacterButton) e.getSource()).getCharacter().getPossibleStartingLocations(), "Select a start location");
					startingLocation = (dwelling) selectedLocation.getSelected();
				}
			});
			group.add(cb);
			characterButtons.add(cb);
			cButtons.add(cb);
		}
		
		group.setSelected(cButtons.get(0).getModel(), true);
		startingLocation = dwelling.inn;
		
		this.add(characterButtons, BorderLayout.NORTH);
	}
	
	public MRCharacter getSelectedCharacter() {
		return getSelectedCharacterButton().getCharacter();
	}
	
	public CharacterButton getSelectedCharacterButton() {
		Enumeration<AbstractButton> itr = group.getElements();
		while(itr.hasMoreElements()) {
			CharacterButton cb = (CharacterButton) itr.nextElement();
			if(cb.isSelected())
				return cb;
		}
		return null;
	}
	
	public dwelling getStartingLocation() {
		return startingLocation;
	}
	
	private class CharacterButton extends JToggleButton {
		private MRCharacter type;
		
		public CharacterButton(MRCharacter type) {
			this.type = type;
			this.setText(this.type.getName());
		}
		
		public MRCharacter getCharacter() {
			return type;
		}
	}

}
