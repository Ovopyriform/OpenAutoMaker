package org.openautomaker.base.inject.importer;

import java.util.List;

import org.openautomaker.base.importers.twod.svg.StylusMetaToGCodeEngine;
import org.openautomaker.base.importers.twod.svg.metadata.dragknife.StylusMetaPart;

public interface StylusMetaToGCodeEngineFactory {

	public StylusMetaToGCodeEngine create(
			String outputURIString,
			List<StylusMetaPart> metaparts);

}
