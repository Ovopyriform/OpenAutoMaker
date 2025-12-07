/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celtech.appManager;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ianhudson
 */
@Singleton
public class ApplicationStatus {

	private final ObjectProperty<ApplicationMode> currentMode = new SimpleObjectProperty<>(null);
	private final StringProperty modeStringProperty = new SimpleStringProperty();
	private final StringProperty modeDisplayStringProperty = new SimpleStringProperty();
	private boolean expertMode = false;
	private final DoubleProperty averageTimePerFrameProperty = new SimpleDoubleProperty(0);
	private static ApplicationMode lastMode = null;

	//TODO: Kept for static reference for the moment
	private static ApplicationStatus instance;

	@Inject
	protected ApplicationStatus() {
		instance = this;
	}

	/**
	 *
	 * @return
	 */
	@Deprecated
	public static ApplicationStatus getInstance() {
		return instance;
	}

	/**
	 *
	 * @param newMode
	 */
	public void setMode(ApplicationMode newMode) {
		ApplicationMode currMode = currentMode.get();

		if (currMode != ApplicationMode.ABOUT
				&& currMode != ApplicationMode.PURGE
				&& currMode != ApplicationMode.CALIBRATION_CHOICE
				&& currMode != ApplicationMode.EXTRAS_MENU
				&& currMode != ApplicationMode.LIBRARY) {
			lastMode = currMode;
		}
		currentMode.setValue(newMode);
	}

	/**
	 *
	 * @return
	 */
	public final ApplicationMode getMode() {
		return currentMode.getValue();
	}

	/**
	 *
	 * @return
	 */
	public final ObjectProperty<ApplicationMode> modeProperty() {
		return currentMode;
	}

	/**
	 *
	 * @param isExpertMode
	 */
	public void setExpertMode(boolean isExpertMode) {
		expertMode = isExpertMode;
	}

	/**
	 *
	 * @return
	 */
	public boolean isExpertMode() {
		return expertMode;
	}

	/**
	 *
	 * @param value
	 */
	public final void setAverageTimePerFrame(double value) {
		averageTimePerFrameProperty.set(value);
	}

	/**
	 *
	 * @return
	 */
	public final double getAverageTimePerFrame() {
		return averageTimePerFrameProperty.get();
	}

	/**
	 *
	 * @return
	 */
	public final DoubleProperty averageTimePerFrameProperty() {
		return averageTimePerFrameProperty;
	}

	public void returnToLastMode() {
		if (lastMode != null) {
			setMode(lastMode);
		}
	}
}
