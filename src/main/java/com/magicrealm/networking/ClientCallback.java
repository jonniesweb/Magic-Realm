package com.magicrealm.networking;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.models.ClientGameState;

/**
 * The client-side portion of the Server-calls-client rmi callback system. This
 * class implements {@link RMIService}. It invokes all method calls on the
 * {@link ClientService} class. 
 */
public class ClientCallback extends UnicastRemoteObject implements RMIService {
	
	protected ClientCallback() throws RemoteException {
	}

	private final Log log = LogFactory.getLog(ClientCallback.class);
	
	@Override
	public Object invoke(String methodName, Class<?>[] methodParameterTypes,
			Object[] args, String client) throws Throwable {
		try {
			// log method call
			if (log.isDebugEnabled()) {
				log.debug("Server [" + client + "] calling method ["
						+ methodName + "] with values [" + args + "]");
			}
			
			IClientService service = new ClientService(ClientGameState.getInstance());
			
			Method method = service.getClass().getMethod(methodName, methodParameterTypes);
			return method.invoke(service, args);
			
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
