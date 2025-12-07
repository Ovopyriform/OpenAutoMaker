package org.openautomaker.base.inject.state_transition;

import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager.StateTransitionActionsFactory;
import org.openautomaker.base.printerControl.model.statetransitions.StateTransitionManager.TransitionsFactory;
import org.openautomaker.base.printerControl.model.statetransitions.purge.PurgeStateTransitionManager;

public interface PurgeStateTransitionManagerFactory {

	public PurgeStateTransitionManager create(
			StateTransitionActionsFactory stateTransitionActionsFactory,
			TransitionsFactory transitionsFactory);
}
