package com.magicrealm.networking;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.magicrealm.client.ClientGameState;
import com.magicrealm.models.board.MagicRealmHexEngineModel;
import com.magicrealm.server.ServerGameState;
import com.magicrealm.utils.Config;

public abstract class AbstractMRTest {
	
	protected static INet service;
	protected static Set<IClientService> clientService;

	@BeforeClass
	public static void setup() throws RemoteException {
		Config.setTestMode(true);
		
		int port = new Random().nextInt(1000) + 60000;
		RMIClient rmiClient = getTestRMIClient("localhost", port);
		
		RMIServer rmiServer = new RMIServer(port);
		
		rmiServer.start();
		rmiClient.start();
		
		service = rmiClient.getService();
		clientService = ServerGameState.getInstance().getClientServices();
	}
	
	@AfterClass
	public static void teardown() {
		// reset ServerGameState singleton to its new self
		new ServerGameState() {
			public void teardown() {
				reset();
			}
		}.teardown();
		
		// reset ClientGameState singleton to its new self
		new ClientGameState() {
			public void teardown() {
				reset();
			}
		}.teardown();
	}
	
	public static void addDefaultCharacter() {
		
	}
	
	private static RMIClient getTestRMIClient(String host, int port) {
		return new RMIClient(host, port) {
			@Override
			protected RMIService getClientCallback() throws RemoteException {
				return getTestClientCallback();
			}
		};
	}
	
	private static IClientService getTestClientService() throws RemoteException {
		return new ClientService(ClientGameState.getInstance()) {
			@Override
			protected void setView(MagicRealmHexEngineModel model) {
				// don't display the ui
			}
		};
	}

	private static ClientCallback getTestClientCallback() throws RemoteException {
		return new ClientCallback() {
			@Override
			protected IClientService getClientService() throws RemoteException {
				return getTestClientService();
			}
		};
	}

}