package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationSingleNozzleHeightActions;
import org.openautomaker.base.task_executor.Cancellable;

import com.google.inject.assistedinject.Assisted;

public interface CalibrationSingleNozzleHeightActionsFactory {

	public CalibrationSingleNozzleHeightActions create(
			Printer printer,
			@Assisted("userCancellable") Cancellable userCancellable,
			@Assisted("errorCancellable") Cancellable errorCancellable,
			boolean safetyFeaturesRequired);
}
