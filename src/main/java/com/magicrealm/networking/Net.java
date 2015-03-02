package com.magicrealm.networking;



class Net implements INet {

	@Override
	public String test() {
		return "Testing 123";
	}

	@Override
	public String test(String string, Object obj) {
		return string + obj;
	}

	@Override
	public void testVoid() {
		// do nothing
	}

	@Override
	public Object test(int integer) {
		return integer;
	}
	
}