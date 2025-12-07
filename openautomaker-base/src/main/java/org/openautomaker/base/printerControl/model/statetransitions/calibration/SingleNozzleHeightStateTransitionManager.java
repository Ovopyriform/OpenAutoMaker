
package org.openautomaker.base.printerControl.model.statetransitions.calibration;

import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager;
import org.openautomaker.base.task_executor.TaskExecutor;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;
import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 *
 * @author tony
 */
public class SingleNozzleHeightStateTransitionManager extends StateTransitionManager<SingleNozzleHeightCalibrationState> {

	@Inject
	public SingleNozzleHeightStateTransitionManager(
			TaskExecutor taskExecutor,
			@Assisted("stateTransitionActionsFactory") StateTransitionActionsFactory stateTransitionActionsFactory,
			@Assisted("transitionsFactory") TransitionsFactory transitionsFactory) {

		super(
				taskExecutor,
				stateTransitionActionsFactory,
				transitionsFactory,
				SingleNozzleHeightCalibrationState.IDLE,
				SingleNozzleHeightCalibrationState.CANCELLING,
				SingleNozzleHeightCalibrationState.CANCELLED,
				SingleNozzleHeightCalibrationState.FAILED);
	}

	public ReadOnlyDoubleProperty getZcoProperty() {
		return ((CalibrationSingleNozzleHeightActions) actions).getZcoGUITProperty();
	}

}
