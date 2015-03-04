package com.magicrealm.networking;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.magicrealm.utils.Config;

public class RMITest {
	
	private static INet service;

	@BeforeClass
	public static void setup() throws RemoteException {
		Config.setTestMode(true);
		
		int port = new Random().nextInt(1000) + 60000;
		RMIClient rmiClient = new RMIClient("localhost", port);
		
		RMIServer rmiServer = new RMIServer(port);
		
		rmiServer.start();
		rmiClient.start();
		
		service = rmiClient.getService();
	}
	
	/**
	 * Creates a client and a server, then calls a few methods from the client.
	 */
	@Test
	public void testConnectToRemoteServer() throws Exception {
		
		assertEquals("Testing 123", service.test());
		assertEquals("1231", service.test("123", new BigDecimal(1)));
		assertEquals("123null", service.test("123", null));
		assertEquals("1232", service.test("123", 2));
		assertEquals(1232, service.test(new Integer(1232)));
		assertEquals(1232, service.test(1232));
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testThrowException() {
		service.testThrow();
	}

}
