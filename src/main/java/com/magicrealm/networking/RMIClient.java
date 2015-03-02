package com.magicrealm.networking;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magicrealm.utils.Config;

public class RMIClient {
	
	private INet service;
	private String host;
	private Integer port;

	/**
	 * @param host
	 * @param port
	 */
	public RMIClient(String host, Integer port) {
		this.host = host;
		this.port = port;
	}

	public RMIClient() {
		this(null, null);
	}

	/**
	 * Creates a new proxy instance for the {@link INet} interface using the
	 * {@link RmiInvocationHandler} class to handle the method invocations
	 */
	public void start() {
		InvocationHandler handler = new RmiInvocationHandler();
		setService((INet) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { INet.class }, handler));
	}
	
	public INet getService() {
		return service;
	}

	public void setService(INet service) {
		this.service = service;
	}

	public String getHost() {
		if (host == null) {
			return Config.getString("rmiServerHostname", "localhost");
		}
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		if (port == null) {
			return Config.getInteger("rmiServerPort", 1099);
		}
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private class RmiInvocationHandler implements InvocationHandler {
		
		private final Log log = LogFactory.getLog(RmiInvocationHandler.class);
		private RMIService rmiService;
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			
			String methodName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			
			// send to the server
			return getRmiService().invoke(methodName, parameterTypes, args, getClient());
		}
		
		private String getClient() {
			return "clientabc";
		}
		
		private RMIService getRmiService() {
			
			if (rmiService == null) {
				if (Config.getBoolean("startServerLocally", false)) {
					
					try {
						startLocalServer();
					} catch (RemoteException | NotBoundException e) {
						log.fatal("Unable to start local server", e);
					}
					
				} else {
					try {
						connectToServer();
					} catch (RemoteException | NotBoundException e) {
						log.fatal("Unable to connect to remote server", e);
					}
					
				}
			}
			return rmiService;
		}
		
		private void connectToServer() throws RemoteException,
		NotBoundException, AccessException {
			Registry registry = LocateRegistry.getRegistry(
					getHost(), getPort());
			rmiService = (RMIService) registry.lookup(RMIService.LOOKUPNAME);
		}
		
		private void startLocalServer() throws RemoteException, NotBoundException {
			RMIServer rmiServerStarter = new RMIServer();
			rmiServerStarter.start();
			rmiService = (RMIService) rmiServerStarter
					.getRegistry().lookup(RMIService.LOOKUPNAME);
		}
		
	}
}
