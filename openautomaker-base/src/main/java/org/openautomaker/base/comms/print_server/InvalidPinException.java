package org.openautomaker.base.comms.print_server;

import java.io.IOException;

public class InvalidPinException extends IOException {

	private static final long serialVersionUID = -5553352404893042627L;

	public InvalidPinException() {
		super();
	}

	public InvalidPinException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPinException(String message) {
		super(message);
	}

	public InvalidPinException(Throwable cause) {
		super(cause);
	}

}
