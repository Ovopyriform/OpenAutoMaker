package org.openautomaker.base.inject.postprocessor;

import java.nio.file.Path;

import org.openautomaker.base.postprocessor.GCodeOutputWriter;

public interface GCodeOutputWriterFactory {

	public GCodeOutputWriter create(Path fileLocation);

}
