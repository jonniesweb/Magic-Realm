package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.magicrealm.utils.GameLog;

public class ConsoleLog extends JPanel {
	
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextPane textPane = new JTextPane();
	
	/**
	 * Create the panel
	 */
	public ConsoleLog() {
		setBounds(100, 100, 700, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		textPane.setDocument(GameLog.getDocument());
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		scrollPane.setMaximumSize(new Dimension(100, 75));
		
		add(scrollPane, BorderLayout.CENTER);
		
		GameLog.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
//				scrollPane.scrollRectToVisible(textPane.getBounds());
			}
		});
		
//		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//			@Override
//			public void adjustmentValueChanged(AdjustmentEvent e) {
//				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//			}
//		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		// set maximum size for this component when used in BorderLayout
		return new Dimension(100, 75);
	}
}
