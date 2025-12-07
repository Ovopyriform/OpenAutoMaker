
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
public class NozzleHeightStateTransitionManager extends StateTransitionManager<NozzleHeightCalibrationState> {

	@Inject
	public NozzleHeightStateTransitionManager(
			TaskExecutor taskExecutor,
			@Assisted("stateTransitionActionsFactory") StateTransitionActionsFactory stateTransitionActionsFactory,
			@Assisted("transitionsFactory") TransitionsFactory transitionsFactory) {

		super(
				taskExecutor,
				stateTransitionActionsFactory,
				transitionsFactory,
				NozzleHeightCalibrationState.IDLE,
				NozzleHeightCalibrationState.CANCELLING,
				NozzleHeightCalibrationState.CANCELLED,
				NozzleHeightCalibrationState.FAILED);
	}

	public ReadOnlyDoubleProperty getZcoProperty() {
		return ((CalibrationNozzleHeightActions) actions).getZcoGUITProperty();
	}

}
