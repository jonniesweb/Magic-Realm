package com.magicrealm.networking;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Random;

import org.junit.Test;

import com.magicrealm.utils.Config;

public class RMIClientTest {
	
	@Test
	public void testConnectToRemoteServer() throws Exception {
		
		Config.setTestMode(true);
		
		int port = new Random().nextInt(1000) + 60000;
		RMIClient rmiClient = new RMIClient("localhost", port);
		
		RMIServer rmiServer = new RMIServer(port);
		
		rmiServer.start();
		rmiClient.start();
		
		INet service = rmiClient.getService();
		
		assertEquals("Testing 123", service.test());
		assertEquals("1231", service.test("123", new BigDecimal(1)));
		assertEquals("123null", service.test("123", null));
		assertEquals("1232", service.test("123", 2));
		assertEquals(1232, service.test(new Integer(1232)));
		assertEquals(1232, service.test(1232));
		service.testVoid();
		
	}

}
