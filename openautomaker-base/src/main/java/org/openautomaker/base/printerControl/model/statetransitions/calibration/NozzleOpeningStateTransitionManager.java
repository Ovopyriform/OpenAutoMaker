
package org.openautomaker.base.printerControl.model.statetransitions.calibration;

import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager;
import org.openautomaker.base.task_executor.TaskExecutor;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;
import javafx.beans.property.ReadOnlyFloatProperty;

/**
 *
 * @author tony
 */
public class NozzleOpeningStateTransitionManager extends StateTransitionManager<NozzleOpeningCalibrationState> {

	@Inject
	protected NozzleOpeningStateTransitionManager(
			TaskExecutor taskExecutor,
			@Assisted("stateTransitionActionsFactory") StateTransitionActionsFactory stateTransitionActionsFactory,
			@Assisted("transitionsFactory") TransitionsFactory transitionsFactory) {

		super(taskExecutor, stateTransitionActionsFactory, transitionsFactory, NozzleOpeningCalibrationState.IDLE,
				NozzleOpeningCalibrationState.CANCELLING, NozzleOpeningCalibrationState.CANCELLED,
				NozzleOpeningCalibrationState.FAILED);
	}

	public ReadOnlyFloatProperty getBPositionProperty() {
		return ((CalibrationNozzleOpeningActions) actions).getBPositionGUITProperty();
	}

}
