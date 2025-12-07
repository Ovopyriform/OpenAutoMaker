package org.openautomaker.base.postprocessor;

import java.io.IOException;

//TODO: Look at this extending the interfaces implemented by Writer so extensions can be Writers rather than separate branch.
public interface GCodeOutputWriter {

	/**
	 * 
	 * @throws IOException
	 */
	void close() throws IOException;

	void flush() throws IOException;

	/**
	 * @return the numberOfLinesOutput
	 */
	int getNumberOfLinesOutput();

	void newLine() throws IOException;

	void writeOutput(String outputLine) throws IOException;

	public void incrementLinesOfOutput(int numberToIncrementBy);
}
