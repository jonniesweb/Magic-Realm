package com.magicrealm.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ServerConnectPane extends JPanel {
	private final JLabel lblIpAddress = new JLabel("IP Address");
	private final JTextField textField = new JTextField();
	private final JButton btnConnect = new JButton("Connect");
	private final JButton btnConnectToLocalhost = new JButton("Connect to Local server");
	
	public ServerConnectPane() {
		textField.setColumns(10);
		setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		add(lblIpAddress, "cell 0 0,alignx trailing");
		add(textField, "cell 1 0,growx");
		add(btnConnect, "flowx,cell 1 1");
		add(btnConnectToLocalhost, "cell 1 1");
	}
	
	public void setConnectAction(ActionListener al) {
		btnConnect.addActionListener(al);
	}
	
	public void setConnectLocallyButton(ActionListener al) {
		btnConnectToLocalhost.addActionListener(al);
	}
	
	public String getIpAddress() {
		return textField.getText();
	}
}
