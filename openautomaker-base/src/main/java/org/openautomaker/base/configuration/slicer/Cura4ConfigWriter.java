package org.openautomaker.base.configuration.slicer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.datafileaccessors.PrintProfileSettingsContainer;
import org.openautomaker.base.slicer.SlicerManager;
import org.openautomaker.environment.Slicer;

import jakarta.inject.Inject;

/**
 *
 * @author George Salter
 */
public class Cura4ConfigWriter extends AbstractSlicerConfigWriter {

	@Inject
	public Cura4ConfigWriter(
			SlicerManager slicerManager,
			PrintProfileSettingsContainer printProfileSettingsContainer) {

		super(slicerManager);

		printProfileSettingsContainer
				.getDefaultPrintProfileSettingsForSlicer(slicerManager.getSlicer())
				.getAllSettings()
				.forEach(setting -> printProfileSettingsMap.put(setting.getId(), setting));
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, boolean value) throws IOException {
		writer.append(variableName + "=" + value + "\n");
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, int value) throws IOException {
		writer.append(variableName + "=" + value + "\n");
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, float value) throws IOException {
		writer.append(variableName + "=" + threeDPformatter.format(value) + "\n");
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, String value) throws IOException {
		writer.append(variableName + "=" + value + "\n");
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, Slicer value) throws IOException {
		writer.append(variableName + "=" + value + "\n");
	}

	@Override
	public void outputLine(FileWriter writer, String variableName, Enum value) throws IOException {
		writer.append(variableName + "=" + value.name().toLowerCase() + "\n");
	}

	@Override
	public void outputPrintCentre(FileWriter writer, float centreX, float centreY) throws IOException {
	}

	@Override
	public void outputFilamentDiameter(FileWriter writer, float diameter) throws IOException {
		outputLine(writer, "material_diameter", String.format(Locale.UK, "%f", diameter));
	}

	@Override
	public void bringDataInBounds(RoboxProfile profileData) {
	}

}
