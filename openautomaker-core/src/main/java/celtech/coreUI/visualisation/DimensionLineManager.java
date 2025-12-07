package celtech.coreUI.visualisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openautomaker.ui.inject.visualisation.DimensionLineFactory;
import org.openautomaker.ui.state.ProjectGUIStates;

import com.google.inject.assistedinject.Assisted;

import celtech.appManager.Project;
import celtech.coreUI.visualisation.DimensionLine.LineDirection;
import celtech.modelcontrol.ModelContainer;
import celtech.modelcontrol.ProjectifiableThing;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ian
 */
public class DimensionLineManager {

	private final Map<ProjectifiableThing, List<DimensionLine>> dimensionLines = new HashMap<>();

	private final ChangeListener<Boolean> dragModeListener = (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
		for (List<DimensionLine> dimensionLineList : dimensionLines.values()) {
			for (DimensionLine dimensionLine : dimensionLineList) {
				dimensionLine.getDimensionLabel().setVisible(!newValue);
			}
		}
	};

	@Inject
	public DimensionLineManager(
			ProjectGUIStates projectGUIStates,
			DimensionLineFactory dimensionLineFactory,
			@Assisted Pane paneToAddDimensionsTo,
			@Assisted Project project,
			@Assisted ReadOnlyBooleanProperty hideDimensionsProperty) {

		hideDimensionsProperty.addListener(dragModeListener);

		projectGUIStates.get(project).getProjectSelection().addListener(
				new ProjectSelection.SelectedModelContainersListener() {

					@Override
					public void whenAdded(ProjectifiableThing projectifiableThing) {
						boolean addDimensionLines = false;
						ArrayList<DimensionLine> lineList = new ArrayList<>();
						DimensionLine verticalDimension = dimensionLineFactory.create();
						DimensionLine frontBackDimension = dimensionLineFactory.create();
						DimensionLine horizontalDimension = dimensionLineFactory.create();

						if (projectifiableThing instanceof ScreenExtentsProviderTwoD) {
							projectifiableThing.addScreenExtentsChangeListener(verticalDimension);
							paneToAddDimensionsTo.getChildren().add(verticalDimension);
							verticalDimension.initialise(project,
									projectifiableThing,
									LineDirection.VERTICAL);
							lineList.add(verticalDimension);

							projectifiableThing.addScreenExtentsChangeListener(horizontalDimension);
							paneToAddDimensionsTo.getChildren().add(horizontalDimension);
							horizontalDimension.initialise(project, projectifiableThing, LineDirection.HORIZONTAL);
							lineList.add(horizontalDimension);

							paneToAddDimensionsTo.getChildren().add(verticalDimension.getDimensionLabel());
							paneToAddDimensionsTo.getChildren().add(horizontalDimension.getDimensionLabel());
							addDimensionLines = true;
						}

						if (projectifiableThing instanceof ScreenExtentsProviderThreeD) {

							projectifiableThing.addScreenExtentsChangeListener(frontBackDimension);
							paneToAddDimensionsTo.getChildren().add(frontBackDimension);
							frontBackDimension.initialise(project, projectifiableThing, LineDirection.FORWARD_BACK);
							lineList.add(frontBackDimension);

							paneToAddDimensionsTo.getChildren().add(frontBackDimension.getDimensionLabel());
						}

						if (addDimensionLines) {
							dimensionLines.put(projectifiableThing, lineList);

							Platform.runLater(() -> {
								verticalDimension.screenExtentsChanged(projectifiableThing);
								horizontalDimension.screenExtentsChanged(projectifiableThing);
								if (projectifiableThing instanceof ModelContainer) {
									frontBackDimension.screenExtentsChanged(projectifiableThing);
								}
							});
						}
					}

					@Override

					public void whenRemoved(ProjectifiableThing projectifiableThing) {
						List<DimensionLine> dimensionLinesToRemove = dimensionLines
								.get(projectifiableThing);
						dimensionLinesToRemove.forEach(line -> {
							projectifiableThing.removeScreenExtentsChangeListener(line);
							paneToAddDimensionsTo.getChildren().remove(line);
							paneToAddDimensionsTo.getChildren().remove(line.getDimensionLabel());
						});
						dimensionLines.remove(projectifiableThing);
					}
				});
	}
}
