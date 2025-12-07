package org.openautomaker.base.printerControl;

/**
 *
 * @author ianhudson
 */
public enum PrinterStatus {

	IDLE("printerStatus.idle"),
	PRINTING_PROJECT("printerStatus.printing"),
	RUNNING_TEST("printerStatus.runningTest"),
	RUNNING_MACRO_FILE("printerStatus.executingMacro"),
	REMOVING_HEAD("printerStatus.removingHead"),
	PURGING_HEAD("printerStatus.purging"),
	OPENING_DOOR("printerStatus.openingDoor"),
	CALIBRATING_NOZZLE_ALIGNMENT("printerStatus.calibratingNozzleAlignment"),
	CALIBRATING_NOZZLE_HEIGHT("printerStatus.calibratingNozzleHeight"),
	CALIBRATING_NOZZLE_OPENING("printerStatus.calibratingNozzleOpening");

	private final String i18nKey;

	private PrinterStatus(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getKey() {
		return i18nKey;
	}
}
