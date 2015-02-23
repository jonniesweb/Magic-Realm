package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.igormaznitsa.jhexed.engine.DefaultIntegerHexModel;
import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

public class BoardView implements Observer {

	private MagicRealmHexEngineModel model;
	JComponent gameboardComponent;
	
	public BoardView(MagicRealmHexEngineModel model) {
		this.model = model;
		model.addObserver(this);
		run();
	}

	private void run() {
		final JFrame frame = new JFrame("Magic Realms - NullPointerException expansion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		final HexEngine<Graphics2D> engine = new HexEngine<Graphics2D>(200, 200, 0.25f, HexEngine.ORIENTATION_HORIZONTAL);
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
			
			@Override
			public void mouseClicked(MouseEvent e) {
				final HexPosition position = engine.pointToHex(e.getX(), e.getY());
				if (engine.getModel().isPositionValid(position)) {
					final DefaultIntegerHexModel model = (DefaultIntegerHexModel) engine.getModel();
					Integer value = model.getValueAt(position);
					if (value > 7) {
						value = 0;
					} else {
						value++;
					}
					model.setValueAt(position, value);
				}
				gameboardComponent.repaint();
			}
			
		});
		
		JScrollPane jScrollPane = new JScrollPane(gameboardComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setPreferredSize(new Dimension(1000, 600));
		
		frame.add(jScrollPane, BorderLayout.CENTER);
		frame.add(new ActivityView(), BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		gameboardComponent.repaint();
		
	}
}