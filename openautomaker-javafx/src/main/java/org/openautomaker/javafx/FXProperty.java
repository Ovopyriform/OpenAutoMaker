package org.openautomaker.javafx;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;
import org.openautomaker.environment.preference.ASimpleFloatPreference;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;

public final class FXProperty {

	/**
	 * Binds a Boolean Preference to a JavaFX property
	 * 
	 * @param booleanPreference - The preference to bind
	 * @return BooleanProperty bound to preference
	 */
	public static BooleanProperty bind(ASimpleBooleanPreference booleanPreference) {
		BooleanProperty booleanProperty = new SimpleBooleanProperty(booleanPreference.getValue());

		booleanPreference.addChangeListener((evt) -> {
			booleanProperty.set(booleanPreference.getValue());
		});

		booleanProperty.addListener((observable, oldValue, newValue) -> {
			booleanPreference.setValue(newValue);
		});

		return booleanProperty;
	}

	/**
	 * Binds a Float Preference to a JavaFX property
	 * 
	 * @param floatPreference - the preference to bind
	 * @return FloatProperty bound to preference
	 */
	public static FloatProperty bind(ASimpleFloatPreference floatPreference) {
		FloatProperty floatProperty = new SimpleFloatProperty(floatPreference.getValue());

		floatPreference.addChangeListener((evt) -> {
			floatProperty.set(floatPreference.getValue());
		});

		floatProperty.addListener((observable, oldValue, newValue) -> {
			floatPreference.setValue((Float) newValue);
		});

		return floatProperty;
	}
}
