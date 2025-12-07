package org.openautomaker.base.comms.print_server;

import java.io.IOException;

public class InvalidResponseException extends IOException {

	private static final long serialVersionUID = -5674382124704406643L;

	public InvalidResponseException() {
		super();
	}

	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidResponseException(String message) {
		super(message);
	}

	public InvalidResponseException(Throwable cause) {
		super(cause);
	}
}
