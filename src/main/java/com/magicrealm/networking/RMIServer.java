package com.magicrealm.networking;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.magicrealm.utils.Config;

public class RMIServer {
	
	private Registry registry;
	private RMIServiceImpl service;
	private Integer port;

	public RMIServer(int port) {
		setPort(port);
	}
	
	public RMIServer() {
	}

	public void start() throws RemoteException {
		setRegistry(LocateRegistry.createRegistry(getPort()));
		setService(new RMIServiceImpl());
		getRegistry().rebind(RMIService.LOOKUPNAME, getService());
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public RMIServiceImpl getService() {
		return service;
	}

	public void setService(RMIServiceImpl service) {
		this.service = service;
	}

	public Integer getPort() {
		if (port == null) {
			return Config.getInteger("rmiServerPort", 1099);
		}
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
}
