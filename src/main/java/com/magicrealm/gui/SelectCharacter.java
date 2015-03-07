package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.magicrealm.models.Amazon;
import com.magicrealm.models.Captain;
import com.magicrealm.models.Dwelling.dwelling;
import com.magicrealm.models.MRCharacter;
import com.magicrealm.models.Swordsman;
import com.magicrealm.models.tiles.GameTile.TileType;

public class SelectCharacter extends JPanel {
	
	private CharacterButton amazon;
	private CharacterButton captain;
	private CharacterButton swordsman;
	private ButtonGroup group;
	private JPanel characterButtons;
	private JComboBox<String> startLocations;
	private String[] captainLocations;
	
	public SelectCharacter() {
		setLayout(new BorderLayout());
		
		group = new ButtonGroup();
		
		captainLocations = new String[] {"Inn", "House", "Guard House"};
		
		characterButtons = new JPanel();
		characterButtons.setLayout(new GridLayout(1, 3));
		
		amazon = new CharacterButton(new Amazon());
		amazon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startLocations.setVisible(false);
			}
		});
		group.add(amazon);
		characterButtons.add(amazon);
		captain = new CharacterButton(new Captain());
		captain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startLocations.setModel(new DefaultComboBoxModel<String>(captainLocations));
				startLocations.setVisible(true);
				
			}
		});
		group.add(captain);
		characterButtons.add(captain);
		swordsman = new CharacterButton(new Swordsman());
		swordsman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startLocations.setVisible(false);
			}
		});
		group.add(swordsman);
		characterButtons.add(swordsman);
		
		group.setSelected(amazon.getModel(), true);
		
		this.add(characterButtons, BorderLayout.NORTH);
		
		startLocations = new JComboBox<String>(captainLocations);
		startLocations.setVisible(false);
		
		this.add(startLocations, BorderLayout.CENTER);
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
	
	public TileType getTileType() {
		if(group.getSelection() == captain.getModel()) {
			switch ((String) startLocations.getSelectedItem()) {
			case "Inn":
				return TileType.BV;
			case "House":
				return TileType.CV;
			case "Guard House":
				return TileType.DV;
			}
		}
		return TileType.BV;
	}
	
	public dwelling getStartingLocation() {
		return dwelling.inn;
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
