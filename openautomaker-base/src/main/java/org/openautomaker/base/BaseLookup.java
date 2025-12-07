package org.openautomaker.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 *
 * @author ianhudson
 */
//TODO: Literally nothing left in this class apart from shutting down flag. Probably move that somewhere
@Singleton
public class BaseLookup {
	private static final Logger LOGGER = LogManager.getLogger();

	private boolean shuttingDown = false;

	@Inject
	protected BaseLookup() {
	}

	@Deprecated
	public boolean isShuttingDown() {
		return shuttingDown;
	}

	public void setShuttingDown(boolean shuttingDown) {
		this.shuttingDown = shuttingDown;
	}
}
