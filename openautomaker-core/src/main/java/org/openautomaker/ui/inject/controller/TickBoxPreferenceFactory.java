package org.openautomaker.ui.inject.controller;

import celtech.coreUI.controllers.panels.userpreferences.TickBoxPreference;
import javafx.beans.property.BooleanProperty;

public interface TickBoxPreferenceFactory {

	public TickBoxPreference create(
			BooleanProperty booleanProperty,
			String caption);
}
