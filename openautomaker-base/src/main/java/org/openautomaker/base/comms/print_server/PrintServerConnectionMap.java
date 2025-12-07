package org.openautomaker.base.comms.print_server;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.openautomaker.base.inject.comms.PrintServerConnectionFactory;

import jakarta.inject.Inject;

//TODO: I don't think this is needed if we have the connection manager.
public class PrintServerConnectionMap extends ConcurrentHashMap<InetAddress, PrintServerConnection> {

	private static final long serialVersionUID = -6916187350399608156L;
	private final PrintServerConnectionFactory detectedServerFactory;

	@Inject
	protected PrintServerConnectionMap(
			PrintServerConnectionFactory detectedServerFactory) {

		super();

		this.detectedServerFactory = detectedServerFactory;
	}

	@Override
	public PrintServerConnection get(Object inetAddress) {
		if (!(inetAddress instanceof InetAddress))
			return null;

		PrintServerConnection printServer = super.get(inetAddress);
		if (printServer != null)
			return printServer;

		printServer = detectedServerFactory.create((InetAddress) inetAddress);

		//Blocking put on the super
		PrintServerConnection fromPut = super.putIfAbsent((InetAddress) inetAddress, printServer);
		if (fromPut != null)
			return fromPut;

		return printServer;
	}
}
