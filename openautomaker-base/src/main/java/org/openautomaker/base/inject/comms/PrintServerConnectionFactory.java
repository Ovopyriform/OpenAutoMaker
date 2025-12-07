package org.openautomaker.base.inject.comms;

import java.net.InetAddress;

import org.openautomaker.base.comms.print_server.PrintServerConnection;

public interface PrintServerConnectionFactory {

	public PrintServerConnection create();

	public PrintServerConnection create(InetAddress address);
}
