package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.Placeable;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.models.tiles.GameTile;
import com.magicrealm.models.tiles.TileClearing;
import com.magicrealm.utils.TileClearingLocation;

public class BoardComponent {

	private MagicRealmHexEngineModel model;
	private ConsoleLog consoleLogFrame = new ConsoleLog();
	private HexEngine<Graphics2D> engine;
	private JComponent gameboardComponent;
	private JLabel lblGold = new JLabel("Gold");
	private final Log log = LogFactory.getLog(BoardComponent.class);
	private ClientGameState gameState;
	private UIMediator mediator;
	
	/**
	 * View for the Client.
	 * It is assumed that the model exists at the creation of this object.
	 * @param gameState
	 * @param model
	 */
	public BoardComponent(ClientGameState gameState, MagicRealmHexEngineModel model) {
		this.gameState = gameState;
		this.model = model;
		
		// set property change support for the ClientGameState's model
		gameState.getPcs().addPropertyChangeListener("model", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// call updateModel with the new model from gamestate
				updateModel(BoardComponent.this.gameState.getModel());
			}
		});
		
		// init the view
		run();
	}

	private void run() {
		
		final JFrame frame = new JFrame("Magic Realm - Team 25");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		engine = new HexEngine<Graphics2D>(200, 200, 0.25f, HexEngine.ORIENTATION_HORIZONTAL);
		engine.setModel(model);
		
		engine.setRenderer(new HexImageRenderer());
		
		
		gameboardComponent = new JComponent() {
			
			@Override
			protected void paintComponent(Graphics g) {
				engine.draw((Graphics2D) g);
			}
			
			@Override
			public Dimension getPreferredSize() {
				final HexRect2D rect = engine.getVisibleSize();
				return new Dimension(rect.getWidthAsInt(), rect.getHeightAsInt());
			}
		};
		
		// add a listener for finding the clearing that the cursor clicked on
		gameboardComponent.addMouseListener(new MouseAdapter() {
			TileClearing closest = null;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// get the hex tile position of the tile that was clicked on
				final HexPosition position = engine.pointToHex(e.getX(), e.getY());
				if (engine.getModel().isPositionValid(position)) {
					
					// get the hex tile from the hex position
					final MagicRealmHexEngineModel model = (MagicRealmHexEngineModel) engine.getModel();
					GameTile tile = model.getValueAt(position);
					if (tile == null) {
						return;
					}
					
					// hex tile anchor point
					float hexx = engine.calculateX(position.getColumn(), position.getRow());
					float hexy = engine.calculateY(position.getColumn(), position.getRow());

					// positions relative to the hex tile anchor point
					float ihexx = e.getX() - hexx;
					float ihexy = e.getY() - hexy;
					
					double distance = 100000000000000d; // big number
					Point scaledClearingCoords = null;
					
					// iterate over clearings to find the one closest to the cursor
					for (TileClearing clearing : tile.getClearings()) {
						if (clearing == null) {
							continue;
						}
						Point rotatedPoint = HexImageRenderer.rotateCoordinates(clearing.getXPosition(), clearing.getYPosition(), tile.getRotation());
						scaledClearingCoords = HexImageRenderer.scaleCoordinates(engine, rotatedPoint);
						
						log.trace("point coords " + clearing.getClearingNumber() + " "+ scaledClearingCoords);
						double distanceToClearing = scaledClearingCoords.distance(ihexx, ihexy);
						if (distanceToClearing < distance) {
							log.trace("found closer " + distanceToClearing);
							closest = clearing;
							distance = distanceToClearing;
						}
					}
					TileClearingLocation location = new TileClearingLocation(tile.getTileType(), closest.getClearingNumber());
					mediator.clearingSelected(location, closest);
					
					log.trace("tile "
							+ tile.getTileType()
							+ " x "
							+ e.getX()
							+ " y "
							+ e.getY()
							+ " hx "
							+ hexx
							+ " hy "
							+ hexy
							+ " ihx "
							+ ihexx
							+ " ihy "
							+ ihexy);
					log.trace("closest "
							+ closest.getClearingNumber()
							+ " distance "
							+ scaledClearingCoords);
					
					if (closest != null) {
						for (Placeable placeable : closest.getChits()) {
							log.debug("chit in clearing: " + placeable);
						}
					}
				}
			}
		});
		
		gameboardComponent.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
		});
		
		JScrollPane jScrollPane = new JScrollPane(gameboardComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setPreferredSize(new Dimension(1000, 600));
		
		frame.getContentPane().add(jScrollPane, BorderLayout.CENTER);
		ActivityView activityView = new ActivityView();
		frame.getContentPane().add(activityView, BorderLayout.NORTH);
		activityView.setLayout(new MigLayout("", "[][][][][grow][right]", "[]"));
		
		JButton btnConsoleLog = new JButton("Console Log");
		btnConsoleLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consoleLogFrame.setVisible(true);
			}
		});
		
		// add debug mouse xy cursor
		addXYMouseCursor();
		
		// update gold
		// TODO: fix gold
		final MRCharacter character = ClientGameState.getInstance().getCharacter();
		character.getPropertyChangeSupport().addPropertyChangeListener("gold", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				lblGold.setText("Gold: " + character.getGold());
			}
		});
		
		// set initial value
		lblGold.setText("Gold: " + character.getGold());
		
		activityView.add(lblGold, "cell 3 0");
		activityView.add(btnConsoleLog, "cell 5 0");
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * trash
	 */
	private void addXYMouseCursor() {
		gameboardComponent.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		final XYMouseCursor mouseLabel = new XYMouseCursor();
		JLayeredPane layeredPane = gameboardComponent.getRootPane().getLayeredPane();
		layeredPane.add(mouseLabel, JLayeredPane.DRAG_LAYER);
		mouseLabel.setBounds(0, 0, gameboardComponent.getWidth(), gameboardComponent.getHeight());
		mouseLabel.setVisible(true);
		
//		gameboardComponent.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				System.out.println("move");
//				mouseLabel.x = e.getX();
//				mouseLabel.y = e.getY();
//				mouseLabel.repaint();
//			}
//		});
		
	}
	
	private class XYMouseCursor extends JComponent {
		public int x;
		public int y;
		public XYMouseCursor() {
			this.setBackground(Color.blue);
		}
		
		// use the xy coordinates to update the mouse cursor text/label
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			String s = x + ", " + y;
			g.setColor(Color.red);
			g.drawString(s, x, y);
		}
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		log.debug("updating the model");
		this.model = model;
		engine.setModel(model);
		gameboardComponent.repaint();
		log.debug("Repainting the gameboard");
	}
}