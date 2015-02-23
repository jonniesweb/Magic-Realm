package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.magicrealm.utils.GameLog;

public class ConsoleLog extends JFrame {
	
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextPane textPane = new JTextPane();
	
	/**
	 * Create the frame.
	 */
	public ConsoleLog() {
		super("Console Log");
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		textPane.setDocument(GameLog.getDocument());
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());				
			}
		});
	}
	

}
