package com.magicrealm.server.main;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.networking.RMIServer;

public class ServerMain {
	
	private final Log log = LogFactory.getLog(ServerMain.class);
	
	public ServerMain() {

		try {
			RMIServer rmiServer = new RMIServer();
			rmiServer.start();
			
		} catch (RemoteException e) {
			log.error("unable to start the server", e);
		}
	}
	
	
	public static void main(String[] args) {
		new ServerMain();	
	}
	
}
