package com.magicrealm.networking;


interface INet {
	
	// For testing
	public String test();
	public String test(String string, Object obj);
	public void testThrow() throws NullPointerException;
	public Object test(int integer);
}