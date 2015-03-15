package com.magicrealm.networking;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class RMITest extends AbstractMRTest {
	
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
	
	@Test
	public void testClientCallback() {
		for (IClientService iClientService : clientService) {
			assertEquals("abcdef123", iClientService.test("abcdef"));
		}
	}

}
