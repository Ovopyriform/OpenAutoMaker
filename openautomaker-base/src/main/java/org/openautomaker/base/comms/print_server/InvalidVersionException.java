package org.openautomaker.base.comms.print_server;

import java.io.IOException;

public class InvalidVersionException extends IOException {

	private static final long serialVersionUID = -2815720201344920202L;

	public InvalidVersionException() {
		super();
	}

	public InvalidVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidVersionException(String message) {
		super(message);
	}

	public InvalidVersionException(Throwable cause) {
		super(cause);
	}
}
