package org.openautomaker.base.slicer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.fileRepresentation.SlicerMappingData;
import org.openautomaker.environment.Slicer;

public interface SlicerConfigWriter {

	public void generateConfigForSlicer(RoboxProfile profileData, Path destinationFile);

	/**
	 * Generates a .roboxprofile file which takes into account the user slicer preferences,
	 * and the slicermappings.dat file, which will map the options for a particular slicer.
	 * 
	 * @param profileData     the user slicer parameters.
	 * @param destinationFile the destination for the .roboxprofile file.
	 * @param mappingData     The mapping data from the slicermappings.dat file.
	 */
	public void generateConfigForSlicerWithMappings(RoboxProfile profileData, Path destinationFile, SlicerMappingData mappingData);

	public void setPrintCentre(float x, float y);

	//TODO: Remove.  Should be in SlicerManager only
	public Slicer getSlicerType();


	public void outputLine(FileWriter writer, String variableName, boolean value) throws IOException;

	public void outputLine(FileWriter writer, String variableName, int value) throws IOException;

	public void outputLine(FileWriter writer, String variableName, float value) throws IOException;

	public void outputLine(FileWriter writer, String variableName, String value) throws IOException;

	public void outputLine(FileWriter writer, String variableName, Slicer value) throws IOException;

	public void outputLine(FileWriter writer, String variableName, Enum value) throws IOException;

	public void outputPrintCentre(FileWriter writer, float centreX, float centreY) throws IOException;

	public void outputFilamentDiameter(FileWriter writer, float diameter) throws IOException;

	public void bringDataInBounds(RoboxProfile profileData);
}
