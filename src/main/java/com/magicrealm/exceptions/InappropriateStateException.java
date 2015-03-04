package com.magicrealm.exceptions;

import com.magicrealm.server.state.ServerState;

public class InappropriateStateException extends RuntimeException {

	public InappropriateStateException(String string,
			Class<? extends ServerState> class1) {
		super("Unable to " + string + " because " + class1.getName() + " isn't allowed");
	}
	
}
