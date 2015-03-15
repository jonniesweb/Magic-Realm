package com.magicrealm.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.magicrealm.client.ClientGameState;
import com.magicrealm.exceptions.GameAlreadyStartedException;

public class StartGameFrame extends JFrame {
	
	private JPanel contentPane;
	private final JLabel lblWaitingForOther = new JLabel("Start the game when everyone is ready");
	private final JButton btnStartGame = new JButton("Start Game");
	private ClientGameState state;
	
	/**
	 * Create the frame.
	 */
	public StartGameFrame(final ClientGameState state) {
		this.state = state;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		lblWaitingForOther.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(lblWaitingForOther, BorderLayout.NORTH);
		btnStartGame.addActionListener(new ActionListener() {
			// call start game in its own thread or else a deadlock will occur
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						try {
							StartGameFrame.this.state.getService().startGame();
						} catch (GameAlreadyStartedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		
		contentPane.add(btnStartGame, BorderLayout.CENTER);
	}
}
