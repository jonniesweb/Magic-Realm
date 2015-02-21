package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.igormaznitsa.jhexed.engine.DefaultIntegerHexModel;
import com.igormaznitsa.jhexed.engine.HexEngine;
import com.igormaznitsa.jhexed.engine.misc.HexPosition;
import com.igormaznitsa.jhexed.engine.misc.HexRect2D;
import com.magicrealm.models.board.MagicRealmHexEngineModel;

public class BoardView {
	
	private MagicRealmHexEngineModel model;
	
	public BoardView(MagicRealmHexEngineModel model) {
		this.model = model;
		run();
	}

	private void run() {
		final JFrame frame = new JFrame("JHexed");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		final HexEngine<Graphics2D> engine = new HexEngine<Graphics2D>(200, 200, 0.25f, HexEngine.ORIENTATION_HORIZONTAL);
		engine.setModel(model);
		
		engine.setRenderer(new HexImageRenderer());
		
		final JComponent content = new JComponent(){
			
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
		
		content.addMouseListener(new MouseAdapter() {
			
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
				content.repaint();
			}
			
		});
		
		frame.add(content, BorderLayout.CENTER);
		frame.add(new ActivityView(), BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}