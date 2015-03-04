package com.magicrealm.networking;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.server.ServerGameState;

/**
 * The server-side implementation of {@link RMIService} for invoking methods
 * called from the clients. This class is registered on the RMI registry. It
 * then calls an instance of {@link Net}, passing the client id to it.
 */
public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {
	
	private static final long serialVersionUID = -6308121689692145017L;
	private final Log log = LogFactory.getLog(RMIServiceImpl.class);
	
	protected RMIServiceImpl() throws RemoteException {
	}

	@Override
	public Object invoke(String methodName, Class<?>[] methodParameterTypes, Object[] args, String client)
			throws Throwable {
		try {
			// log method call
			if (log.isDebugEnabled()) {
				log.debug("Client [" + client + "] calling method ["
						+ methodName + "] with values [" + args + "]");
			}
			
			// create a new net class, passing in the client's id
			Net net = new Net(client, ServerGameState.getInstance());
			
			// find the method and then invoke it
			Method method = net.getClass().getMethod(methodName, methodParameterTypes);
			return method.invoke(net, args);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error(e);
			
			// if the exception is of InvocationTargetException type, then throw
			// its cause since InvocationTargetException wraps itself around
			// whatever errors occur from the method
			if (e instanceof InvocationTargetException && e.getCause() != null) {
				throw e.getCause();
				
			} else {
				// throw the original error otherwise
				throw e;
			}
		}
	}
	
}
