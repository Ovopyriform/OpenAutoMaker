package org.openautomaker.ui.inject.controller;

import com.google.inject.assistedinject.Assisted;

import celtech.coreUI.controllers.panels.userpreferences.FloatingPointPreference;
import javafx.beans.property.FloatProperty;

public interface FloatingPointPreferenceFactory {

	public FloatingPointPreference create(
			FloatProperty floatProperty,
			@Assisted("decimalPlaces") int decimalPlaces,
			@Assisted("digits") int digits,
			boolean negativeAllowed,
			String caption);

}
