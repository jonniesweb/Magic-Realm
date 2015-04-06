package com.magicrealm.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.chits.ClearingMapChit;
import com.magicrealm.models.chits.SiteChit;
import com.magicrealm.models.chits.SiteChit.site;
import com.magicrealm.models.chits.SoundChit;
import com.magicrealm.models.chits.SoundChit.sound;
import com.magicrealm.models.chits.WarningChit;
import com.magicrealm.models.chits.WarningChit.warning;
import com.magicrealm.models.tiles.GameTile.TileType;

public class SetupChitsPanel extends JPanel {
	
	private final Log log = LogFactory.getLog(SetupChitsPanel.class);
	
	private final JPanel tileListPanel = new JPanel();
	private final JPanel lostCityCastlePanel = new JPanel();
	private List<TileRow> tileRows = new ArrayList<>();
	private List<JTextField> lostCastleRows = new ArrayList<>();
	private List<JTextField> lostCityRows = new ArrayList<>();
	private final JLabel siteChitDescriptionLabel = new JLabel(getSiteChitsDescription());
	private final JLabel soundChitDescriptionLabel = new JLabel(getSoundChitsDescription());
	private final JLabel warningChitDescriptionLabel = new JLabel(getWarningChitsDescription());
	private final JPanel descriptionLabelPanel = new JPanel();
	private final JButton btnSubmit = new JButton("Submit");

	private UIMediator mediator;
	
	/**
	 * Create the panel.
	 */
	public SetupChitsPanel(UIMediator mediator) {
		this.mediator = mediator;
		/*
		 * Setup ui components
		 */
		setLayout(new MigLayout("", "[300px,grow][100px,grow][grow]", "[][]"));
		
		// setup tile site, sound and warming chit input
		add(tileListPanel, "cell 0 0 1 2,grow");
		tileListPanel.setLayout(new BoxLayout(tileListPanel, BoxLayout.Y_AXIS));
		tileListPanel.setBorder(new LineBorder(Color.BLACK));
		
		tileListPanel.add(new JLabel("site/sound chit       warning Chit"));
		for (TileType tile : TileType.values()) {
			TileRow tileRow = new TileRow(tile);
			tileRows.add(tileRow);
			tileListPanel.add(tileRow);
		}
		
		// setup lost city and lost castle chit input
		add(lostCityCastlePanel, "cell 1 0,growx,aligny top");
		lostCityCastlePanel.setLayout(new BoxLayout(lostCityCastlePanel, BoxLayout.Y_AXIS));
		tileListPanel.setBorder(new LineBorder(Color.BLACK));
		
		lostCityCastlePanel.add(new JLabel("Lost City site/sound chit"));
		for (int i = 0; i < 5; i++) {
			JTextField textField = new JTextField();
			textField.setColumns(10);
			lostCityCastlePanel.add(textField);
			lostCityRows.add(textField);
		}
		
		lostCityCastlePanel.add(new JLabel("Lost Castle site/sound chit"));
		for (int i = 0; i < 5; i++) {
			JTextField textField = new JTextField();
			textField.setColumns(10);
			lostCityCastlePanel.add(textField);
			lostCastleRows.add(textField);
		}
		lostCityCastlePanel.add(new JLabel("<html>The Lost City and Lost Castle"
				+ " site/sound chits specified above will be added to the tile"
				+ " that has the lost_city and lost_castle site chit assigned"
				+ " to it, respectively.</html>"));
		
		// add description labels for the chit inputs
		add(descriptionLabelPanel, "cell 2 0,growx,aligny top");
		descriptionLabelPanel.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		descriptionLabelPanel.add(siteChitDescriptionLabel, "cell 0 0");
		descriptionLabelPanel.add(soundChitDescriptionLabel, "cell 0 1");
		descriptionLabelPanel.add(warningChitDescriptionLabel, "cell 0 2");
		
		// add a submit button
		add(btnSubmit, "cell 2 1,alignx right,aligny bottom");
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				SetupChitsPanel.this.mediator.setupChits(getClearingChits(), getWarningChits());
				
			}
		});
	}
	
	private static String getSiteChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>Avaliable Site chits: ");
		for (site s : site.values()) {
			sb.append("<br>");
			sb.append(s);
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	private static String getSoundChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>Avaliable Sound chits: ");
		for (sound s : sound.values()) {
			sb.append("<br>");
			sb.append(s);
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	private static String getWarningChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>Avaliable Warning chits: ");
		for (warning w : warning.values()) {
			sb.append("<br>");
			sb.append(w);
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	/**
	 * Get all warning chits assigned to the tiles
	 * @return
	 */
	public Map<TileType, WarningChit> getWarningChits() {
		EnumMap<TileType, WarningChit> warningMap = new EnumMap<>(TileType.class);
		
		for (TileRow row : tileRows) {
			String warningChitText = row.getWarningChitText();
			
			if (warningChitText.isEmpty())
				continue;
			
			try {
				warningMap.put(row.getTileType(), new WarningChit(warning.valueOf(warningChitText)));
			} catch (IllegalArgumentException e) {
				log.error("unable to parse warning chit from input: " + warningChitText);
			}
		}
		return warningMap;
	}
	
	/**
	 * Get all site and sound chits assigned to the tiles
	 * @return
	 */
	public Map<TileType, ClearingMapChit> getClearingChits() {
		EnumMap<TileType, ClearingMapChit> chitMap = new EnumMap<>(TileType.class);
		
		for (TileRow row : tileRows) {
			String chitText = row.getMapChitText();
			
			if (chitText.isEmpty())
				continue;
			
			// try parsing a site chit
			try {
				chitMap.put(row.getTileType(), new SiteChit(site.valueOf(chitText)));
			} catch (IllegalArgumentException e) {
				
				// try parsing a sound chit
				try {
					chitMap.put(row.getTileType(), new SoundChit(sound.valueOf(chitText)));
				} catch (IllegalArgumentException e1) {
					
					// no luck
					log.warn("unable to parse sound or site chit from input: " + chitText);
				}
			}
		}
		// add chits for lost castle and lost city to their respective chits
		addExtraChits(chitMap, site.lost_castle, getLostCastleChits());
		addExtraChits(chitMap, site.lost_city, getLostCityChits());
		
		return chitMap;
	}

	/**
	 * Add the extra chits specified for lost castle and lost city to its
	 * respective chit.
	 * 
	 * @param chitMap
	 * @param lostCastle
	 * @param extraChits
	 */
	private static void addExtraChits(EnumMap<TileType, ClearingMapChit> chitMap,
			site lostCastle, List<ClearingMapChit> extraChits) {
		for (TileType tileType : chitMap.keySet()) {
			ClearingMapChit chit = chitMap.get(tileType);
			
			if (chit instanceof SiteChit && lostCastle.equals(chit)) {
				SiteChit siteChit = (SiteChit) chit;
				siteChit.getExtraChits().addAll(extraChits);
			}
		}
	}
	
	public List<ClearingMapChit> getLostCityChits() {
		ArrayList<String> list = new ArrayList<String>();
		for (JTextField jTextField : lostCityRows) {
			list.add(jTextField.getText());
		}
		
		return getClearingChits(list);
	}
	
	public List<ClearingMapChit> getLostCastleChits() {
		ArrayList<String> list = new ArrayList<String>();
		for (JTextField jTextField : lostCastleRows) {
			list.add(jTextField.getText());
		}
		
		return getClearingChits(list);
	}
	
	private List<ClearingMapChit> getClearingChits(List<String> rows) {
		ArrayList<ClearingMapChit> list = new ArrayList<>();
		for (String text : rows) {
			
			if (text.isEmpty())
				continue;
			
			// try parsing it as a site chit
			try {
				list.add(new SiteChit(site.valueOf(text)));
			} catch (IllegalArgumentException e) {
				
				// try parsing it as a sound chit
				try {
					list.add(new SoundChit(sound.valueOf(text)));
				} catch (IllegalArgumentException e1) {
					
					// no dice
					log.error("unable to parse a site or sound chit from the string: " + text);
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		final SetupChitsPanel setupChitsPanel = new SetupChitsPanel(new UIMediator());
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.getContentPane().add(setupChitsPanel);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
			}
		});
		
		setupChitsPanel.getClearingChits();
		setupChitsPanel.getWarningChits();
		
	}
	
	/**
	 * Helper class for displaying a row consisting of a label for showing the
	 * tile type, a text field for specifying the map chit, and a text field for
	 * specifying the warning chit.
	 */
	private class TileRow extends JPanel {
		private JTextField mapChit;
		private JTextField warningChit;
		private TileType tileType;
		
		public TileRow(TileType tileType) {
			this.tileType = tileType;
			setLayout(new FlowLayout());
			
			add(new JLabel(tileType.toString()));
			
			mapChit = new JTextField();
			mapChit.setColumns(10);
			add(mapChit);
			
			warningChit = new JTextField();
			warningChit.setColumns(10);
			add(warningChit);
		}
		
		public TileType getTileType() {
			return tileType;
		}
		
		public String getMapChitText() {
			return mapChit.getText();
		}
		
		public String getWarningChitText() {
			return warningChit.getText();
		}
		
	}
}
