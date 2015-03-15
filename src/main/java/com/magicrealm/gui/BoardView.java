package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.characters.MRCharacter;
import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

public class BoardView implements Observer {

	private MagicRealmHexEngineModel model;
	private ConsoleLog consoleLogFrame = new ConsoleLog();
	private HexEngine<Graphics2D> engine;
	private JComponent gameboardComponent;
	private JLabel lblGold = new JLabel("Gold");
	private final Log log = LogFactory.getLog(BoardView.class);
	private ClientGameState gameState;
	
	/**
	 * View for the Client.
	 * It is assumed that the model exists at the creation of this object.
	 * @param gameState
	 * @param model
	 */
	public BoardView(ClientGameState gameState, MagicRealmHexEngineModel model) {
		this.gameState = gameState;
		this.model = model;
		
		// set property change support for the ClientGameState's model
		gameState.getPcs().addPropertyChangeListener("model", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// call updateModel with the new model from gamestate
				updateModel(BoardView.this.gameState.getModel());
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
		
		gameboardComponent = new JComponent(){
			
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
		
		gameboardComponent.addMouseListener(new MouseAdapter() {
			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				final HexPosition position = engine.pointToHex(e.getX(), e.getY());
//				if (engine.getModel().isPositionValid(position)) {
//					final DefaultIntegerHexModel model = (DefaultIntegerHexModel) engine.getModel();
//					Integer value = model.getValueAt(position);
//					if (value > 7) {
//						value = 0;
//					} else {
//						value++;
//					}
//					model.setValueAt(position, value);
//				}
//				gameboardComponent.repaint();
//			}
			
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

	/* 
	 * Repaints the gameboard
	 */
	@Override
	public void update(Observable o, Object arg) {
		engine.setModel(model);
		gameboardComponent.repaint();
		log.debug("Repainting the gameboard");
	}
	
	public void updateModel(MagicRealmHexEngineModel model) {
		log.debug("updating the model");
		this.model = model;
		update(null, null);
	}
}