package com.magicrealm.networking;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;

import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.Config;

public class AbstractMRTest {
	
	protected static INet service;
	protected static Set<IClientService> clientService;

	@BeforeClass
	public static void setup() throws RemoteException {
		Config.setTestMode(true);
		
		int port = new Random().nextInt(1000) + 60000;
		RMIClient rmiClient = new RMIClient("localhost", port);
		
		RMIServer rmiServer = new RMIServer(port);
		
		rmiServer.start();
		rmiClient.start();
		
		service = rmiClient.getService();
		clientService = ServerGameState.getInstance().getClientServices();
	}
	
	public static void addDefaultCharacter() {
		
	}

}