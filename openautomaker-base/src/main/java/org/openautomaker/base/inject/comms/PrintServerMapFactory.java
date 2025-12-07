package org.openautomaker.base.inject.comms;

import org.openautomaker.base.comms.print_server.PrintServerConnectionMap;

public interface PrintServerMapFactory {

	public PrintServerConnectionMap create();
}
