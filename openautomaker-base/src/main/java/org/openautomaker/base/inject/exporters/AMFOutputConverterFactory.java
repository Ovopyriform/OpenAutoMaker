package org.openautomaker.base.inject.exporters;

import org.openautomaker.base.utils.exporters.AMFOutputConverter;

public interface AMFOutputConverterFactory {

	public AMFOutputConverter create();
}
