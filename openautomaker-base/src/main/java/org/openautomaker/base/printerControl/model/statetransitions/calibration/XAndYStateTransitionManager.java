
package org.openautomaker.base.printerControl.model.statetransitions.calibration;

import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager;
import org.openautomaker.base.task_executor.TaskExecutor;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;

/**
 *
 * @author tony
 */
public class XAndYStateTransitionManager extends StateTransitionManager<CalibrationXAndYState> {

	@Inject
	public XAndYStateTransitionManager(
			TaskExecutor taskExecutor,
			@Assisted("stateTransitionActionsFactory") StateTransitionActionsFactory stateTransitionActionsFactory,
			@Assisted("transitionsFactory") TransitionsFactory transitionsFactory) {

		super(
				taskExecutor,
				stateTransitionActionsFactory,
				transitionsFactory,
				CalibrationXAndYState.IDLE,
				CalibrationXAndYState.CANCELLING,
				CalibrationXAndYState.CANCELLED,
				CalibrationXAndYState.FAILED);
	}

	public void setXOffset(String xOffset) {
		((CalibrationXAndYActions) actions).setXOffset(xOffset);
	}

	public void setYOffset(int yOffset) {
		((CalibrationXAndYActions) actions).setYOffset(yOffset);
	}
}
