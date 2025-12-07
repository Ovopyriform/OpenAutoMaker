package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager.StateTransitionActionsFactory;
import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager.TransitionsFactory;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.SingleNozzleHeightStateTransitionManager;

import com.google.inject.assistedinject.Assisted;

public interface SingleNozzleHeightStateTransitionManagerFactory {

	public SingleNozzleHeightStateTransitionManager create(
			@Assisted("stateTransitionActionsFactory") StateTransitionActionsFactory stateTransitionActionsFactory,
			@Assisted("transitionsFactory") TransitionsFactory transitionsFactory);
}
