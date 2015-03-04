package com.magicrealm.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client-Server method reflection. 'nuff said.
 */
public interface RMIService extends Remote {
	
	public static final String LOOKUPNAME = "RMIService";
	
	public Object invoke(String methodName, Class<?>[] methodParameterTypes, Object[] args, String client) throws Throwable;
	
}
