package org.openautomaker.base.inject.state_transition;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationNozzleHeightActions;
import org.openautomaker.base.task_executor.Cancellable;

import com.google.inject.assistedinject.Assisted;

public interface CalibrationNozzleHeightActionsFactory {

	public CalibrationNozzleHeightActions create(
			Printer printer,
			@Assisted("userCancellable") Cancellable userCancellable,
			@Assisted("errorCancellable") Cancellable errorCancellable,
			boolean safetyFeaturesRequired);
}
