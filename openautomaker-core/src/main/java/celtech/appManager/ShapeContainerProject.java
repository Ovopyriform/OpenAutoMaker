package celtech.appManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.configuration.datafileaccessors.CameraProfileContainer;
import org.openautomaker.base.configuration.fileRepresentation.PrinterSettingsOverrides;
import org.openautomaker.base.device.CameraManager;
import org.openautomaker.base.inject.configuration.file_representation.PrinterSettingsOverridesFactory;
import org.openautomaker.environment.I18N;
import org.openautomaker.environment.preference.modeling.ProjectsPathPreference;
import org.openautomaker.environment.preference.slicer.SlicerPreference;
import org.openautomaker.guice.GuiceContext;
import org.openautomaker.ui.inject.model.ModelGroupFactory;
import org.openautomaker.ui.state.SelectedPrinter;

import celtech.configuration.ApplicationConfiguration;
import celtech.configuration.fileRepresentation.ProjectFile;
import celtech.configuration.fileRepresentation.ShapeContainerProjectFile;
import celtech.modelcontrol.Groupable;
import celtech.modelcontrol.ModelGroup;
import celtech.modelcontrol.ProjectifiableThing;
import celtech.utils.threed.importers.svg.ShapeContainer;
import jakarta.inject.Inject;

/**
 *
 * @author ianhudson
 */
public class ShapeContainerProject extends Project {

	private static final Logger LOGGER = LogManager.getLogger(ShapeContainerProject.class.getName());

	@Inject
	public ShapeContainerProject(
			ProjectsPathPreference projectsPathPreference,
			SlicerPreference slicerPreference,
			I18N i18n,
			CameraManager cameraManager,
			GCodeGeneratorManager gCodeGeneratorManager,
			SelectedPrinter selectedPrinter,
			ModelGroupFactory modelGroupFactory,
			CameraProfileContainer cameraProfileContainer,
			PrinterSettingsOverridesFactory printerSettingsOverridesFactory) {

		super(projectsPathPreference, slicerPreference, i18n, cameraManager, gCodeGeneratorManager, modelGroupFactory, cameraProfileContainer, printerSettingsOverridesFactory);
	}

	@Override
	protected void initialise() {
	}

	@Override
	protected void save(Path basePath) {
	}

	@Override
	public void addModel(ProjectifiableThing projectifiableThing) {
		if (projectifiableThing instanceof ShapeContainer) {
			ShapeContainer modelContainer = (ShapeContainer) projectifiableThing;
			topLevelThings.add(modelContainer);
			projectModified();
			fireWhenModelAdded(modelContainer);
		}
	}

	private void fireWhenModelAdded(ShapeContainer modelContainer) {
		for (ProjectChangesListener projectChangesListener : projectChangesListeners) {
			projectChangesListener.whenModelAdded(modelContainer);
		}
	}

	@Override
	public void removeModels(Set<ProjectifiableThing> projectifiableThings) {
		Set<ShapeContainer> modelContainers = (Set) projectifiableThings;

		for (ShapeContainer modelContainer : modelContainers) {
			assert modelContainer != null;
		}

		topLevelThings.removeAll(modelContainers);

		//        for (RenderableSVG modelContainer : modelContainers)
		//        {
		//            removeModelListeners(modelContainer);
		//            for (RenderableSVG childModelContainer : modelContainer.getChildModelContainers())
		//            {
		//                removeModelListeners(childModelContainer);
		//            }
		//        }
		projectModified();
		//        fireWhenModelsRemoved(projectifiableThings);
	}

	@Override
	public void autoLayout() {
	}

	@Override
	public Set<ProjectifiableThing> getAllModels() {
		Set<ProjectifiableThing> allModelContainers = new HashSet<>();
		for (ProjectifiableThing loadedModel : topLevelThings) {
			allModelContainers.add(loadedModel);
		}
		return allModelContainers;
	}

	@Override
	protected void fireWhenModelsTransformed(Set<ProjectifiableThing> projectifiableThings) {
		for (ProjectChangesListener projectChangesListener : projectChangesListeners) {
			projectChangesListener.whenModelsTransformed(projectifiableThings);
		}
	}

	@Override
	protected void fireWhenPrinterSettingsChanged(PrinterSettingsOverrides printerSettings) {
	}

	@Override
	protected void fireWhenTimelapseSettingsChanged(TimelapseSettingsData timelapseSettings) {
	}

	//TODO: Again, odd.  Put in project persistance?
	@Override
	protected void load(ProjectFile projectFile, Path filePath) throws ProjectLoadException {

		if (!(projectFile instanceof ShapeContainerProjectFile))
			throw new ProjectLoadException("Incorrect file type provided");

		suppressProjectChanged = true;

		try {
			projectNameProperty.set(projectFile.getProjectName());
			lastModifiedDate.set(projectFile.getLastModifiedDate());
			lastPrintJobID = projectFile.getLastPrintJobID();

			loadTimelapseSettings(projectFile);

			loadModels(filePath);

		}
		catch (IOException ex) {
			LOGGER.error("Failed to load project " + filePath, ex);
		}
		catch (ClassNotFoundException ex) {
			LOGGER.error("Failed to load project " + filePath, ex);
		}
		finally {
			suppressProjectChanged = false;
		}
	}

	private void loadModels(Path filePath) throws IOException, ClassNotFoundException {

		// Legacy in case we've been passed a path without the project file name
		if (!filePath.toString().endsWith(ApplicationConfiguration.projectFileExtension))
			filePath = filePath.resolveSibling(filePath.getFileName().toString() + ApplicationConfiguration.projectFileExtension);

		//Change the type of the file to the model type
		filePath = filePath.resolveSibling(
				filePath.getFileName().toString()
						.replace(ApplicationConfiguration.projectFileExtension, ApplicationConfiguration.projectModelsFileExtension));

		FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		ObjectInputStream modelsInput = new ObjectInputStream(bufferedInputStream);
		int numModels = modelsInput.readInt();

		for (int i = 0; i < numModels; i++) {
			ShapeContainer modelContainer = (ShapeContainer) modelsInput.readObject();
			GuiceContext.get().injectMembers(modelContainer);
			addModel(modelContainer);
		}
	}

	@Override
	protected void checkNotAlreadyInGroup(Set<Groupable> modelContainers) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ModelGroup createNewGroupAndAddModelListeners(Set<Groupable> modelContainers) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void autoLayout(List<ProjectifiableThing> thingsToLayout) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
