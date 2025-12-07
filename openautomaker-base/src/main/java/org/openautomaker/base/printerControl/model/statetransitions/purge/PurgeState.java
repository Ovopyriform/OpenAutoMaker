/*
 * Copyright 2015 CEL UK
 */
package org.openautomaker.base.printerControl.model.statetransitions.purge;

/**
 *
 * @author tony
 */
public enum PurgeState {

	IDLE("purgeMaterial.explanation", true),
	INITIALISING("purgeMaterial.temperatureInstruction", true),
	CONFIRM_TEMPERATURE("purgeMaterial.temperatureInstruction", true),
	HEATING("purgeMaterial.heating", true),
	RUNNING_PURGE("purgeMaterial.inProgress", true),
	FINISHED("purgeMaterial.purgeComplete", false),
	CANCELLED("purgeMaterial.cancelled", false),
	CANCELLING("purgeMaterial.cancelling", false),
	DONE("purgeMaterial.done", false),
	FAILED("purgeMaterial.failed", false);

	private final String key;
	private boolean showCancel;

	private PurgeState(String key, boolean showCancel) {
		this.key = key;
		this.showCancel = showCancel;
	}

	public boolean showCancelButton() {
		return showCancel;
	}

	public String getKey() {
		return key;
	}

}
