package org.openautomaker.base.inject.printing;

import org.openautomaker.base.services.printing.SFTPUtils;

public interface SFTPUtilsFactory {

	public SFTPUtils create(String hostAddress);
}
