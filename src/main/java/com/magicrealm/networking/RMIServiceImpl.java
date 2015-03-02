package com.magicrealm.networking;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {
	
	private static final long serialVersionUID = -6308121689692145017L;
	private final Log log = LogFactory.getLog(RMIServiceImpl.class);
	private Net net = new Net();

	protected RMIServiceImpl() throws RemoteException {
	}

	@Override
	public Object invoke(String methodName, Class<?>[] methodParameterTypes, Object[] args, String client)
			throws RemoteException {
		try {
			
			Method method = net.getClass().getMethod(methodName, methodParameterTypes);
			return method.invoke(net, args);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error(e);
			throw new RemoteException("Error occurred on server", e);
		}
	}
	
}
