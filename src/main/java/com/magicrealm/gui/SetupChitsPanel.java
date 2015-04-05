package com.magicrealm.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.chits.SiteChit.site;
import com.magicrealm.models.chits.SoundChit.sound;
import com.magicrealm.models.chits.WarningChit.warning;
import com.magicrealm.models.tiles.GameTile.TileType;

public class SetupChitsPanel extends JPanel {
	
	private final Log log = LogFactory.getLog(SetupChitsPanel.class);
	
	private final JPanel tileListPanel = new JPanel();
	private List<TileRow> tileRows = new ArrayList<>();
	
	/**
	 * Create the panel.
	 */
	public SetupChitsPanel() {
		setLayout(new MigLayout("", "[300.00px,grow][100px,grow][grow]", "[][][grow]"));
		
		add(tileListPanel, "cell 0 0,grow");
		tileListPanel.setLayout(new BoxLayout(tileListPanel, BoxLayout.Y_AXIS));
		tileListPanel.setBorder(new LineBorder(Color.BLACK));
		
		for (TileType tile : TileType.values()) {
			TileRow tileRow = new TileRow(tile);
			tileRows.add(tileRow);
			tileListPanel.add(tileRow);
		}
		
	}
	
	// do lost city and lost castle
	
	private static String getSiteChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaliable Site chits: ");
		for (site site : site.values()) {
			sb.append("\n");
			sb.append(site);
		}
		return sb.toString();
	}
	
	private static String getSoundChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaliable Sound chits: ");
		for (sound sound : sound.values()) {
			sb.append("\n");
			sb.append(sound);
		}
		return sb.toString();
	}
	
	private static String getWarningChitsDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaliable Warning chits: ");
		for (warning warning : warning.values()) {
			sb.append("\n");
			sb.append(warning);
		}
		return sb.toString();
	}
	
	/**
	 * Get all warning chits assigned to the tiles
	 * @return
	 */
	public Map<TileType, warning> getWarningChits() {
		EnumMap<TileType, warning> warningMap = new EnumMap<>(TileType.class);
		
		for (TileRow row : tileRows) {
			String warningChitText = row.getWarningChitText();
			
			if (warningChitText.isEmpty())
				continue;
			
			warningMap.put(row.getTileType(),
					warning.valueOf(warningChitText));
		}
		return warningMap;
	}
	
	/**
	 * Get all site chits assigned to the tiles
	 * @return
	 */
	public Map<TileType, site> getSiteChits() {
		EnumMap<TileType, site> siteMap = new EnumMap<>(TileType.class);
		
		for (TileRow row : tileRows) {
			String mapChitText = row.getMapChitText();
			
			if (mapChitText.isEmpty())
				continue;
			
			try {
				siteMap.put(row.getTileType(), site.valueOf(mapChitText));
			} catch (IllegalArgumentException e) {
				log.warn("unable to parse site chit from input: " + mapChitText);
			}
		}
		return siteMap;
	}
	
	/**
	 * Get all sound chits assigned to the tiles
	 * @return
	 */
	public Map<TileType, sound> getSoundChits() {
		EnumMap<TileType, sound> soundMap = new EnumMap<>(TileType.class);
		
		for (TileRow row : tileRows) {
			String mapChitText = row.getMapChitText();
			
			if (mapChitText.isEmpty())
				continue;
			
			try {
				soundMap.put(row.getTileType(), sound.valueOf(mapChitText));
			} catch (IllegalArgumentException e) {
				log.warn("unable to parse sound chit from input: " + mapChitText);
			}
		}
		return soundMap;
	}
	
	public static void main(String[] args) {
		System.out.println(getSiteChitsDescription());
		System.out.println(getSoundChitsDescription());
		System.out.println(getWarningChitsDescription());
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.getContentPane().add(new SetupChitsPanel());
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
			}
		});
		
	}
	
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
