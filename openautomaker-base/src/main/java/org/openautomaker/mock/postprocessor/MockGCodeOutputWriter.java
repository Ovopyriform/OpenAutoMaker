package org.openautomaker.mock.postprocessor;

import java.io.IOException;
import java.nio.file.Path;

import org.openautomaker.base.postprocessor.GCodeOutputWriter;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;

//TODO: Don't actually think I need this.  Test writer can write to temp files so no need for null writer.  Perhaps rename to count writer?
@Deprecated
public class MockGCodeOutputWriter implements GCodeOutputWriter {

	private int numberOfLinesOutput = 0;

	@Inject
	public MockGCodeOutputWriter(
			@Assisted Path filename) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public int getNumberOfLinesOutput() {
		return numberOfLinesOutput;
	}

	@Override
	public void newLine() throws IOException {
	}

	@Override
	public void writeOutput(String outputLine) throws IOException {
		// if it's not a comment or blank line
		if (!outputLine.trim().startsWith(";") && !"".equals(
				outputLine.trim())) {
			numberOfLinesOutput++;
		}
	}

	// Version from NullOutputWriter
	//	@Override
	//	public void writeOutput(String outputLine) throws IOException {
	//		writtenLines.add(outputLine);
	//		numberOfLinesOutput++;
	//	}

	@Override
	public void incrementLinesOfOutput(int numberToIncrementBy) {
		numberOfLinesOutput += numberToIncrementBy;
	}
}
