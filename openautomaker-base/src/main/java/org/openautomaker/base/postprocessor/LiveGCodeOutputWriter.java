package org.openautomaker.base.postprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;

/**
 * OutputWriter is a wrapper to a file writer that allows us to count the number of non-comment and non-blank lines.
 *
 *
 */
public class LiveGCodeOutputWriter implements GCodeOutputWriter {

	//TODO:  Look at this an make the API more like a Writer.  Perhaps extend BufferedWriter and then overload methods where appropriate.

	private int numberOfLinesOutput = 0;
	private BufferedWriter fileWriter = null;

	@Inject
	public LiveGCodeOutputWriter(
			@Assisted Path fileLocation) throws IOException {

		File outputFile = fileLocation.toFile();
		fileWriter = new BufferedWriter(new FileWriter(outputFile));
	}

	@Override
	public void writeOutput(String outputLine) throws IOException {
		fileWriter.write(outputLine);
		// if it's not a comment or blank line
		if (!outputLine.trim().startsWith(";") && !"".equals(
				outputLine.trim())) {
			numberOfLinesOutput++;
		}
	}

	@Override
	public void close() throws IOException {
		fileWriter.close();
	}

	@Override
	public void newLine() throws IOException {
		fileWriter.newLine();
	}

	@Override
	public void flush() throws IOException {
		fileWriter.flush();
	}

	/**
	 * @return the numberOfLinesOutput
	 */
	@Override
	public int getNumberOfLinesOutput() {
		return numberOfLinesOutput;
	}

	@Override
	public void incrementLinesOfOutput(int numberToIncrementBy) {
		numberOfLinesOutput += numberToIncrementBy;
	}
}