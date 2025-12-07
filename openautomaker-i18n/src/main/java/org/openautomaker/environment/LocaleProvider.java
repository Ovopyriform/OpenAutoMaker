package org.openautomaker.environment;

import java.util.Locale;
import java.util.prefs.PreferenceChangeListener;

/**
 * Interface for a LocalProvider for the I18N package. This will generally be provided by a preference.
 */
public interface LocaleProvider {

	public Locale getValue();

	public void addChangeListener(PreferenceChangeListener pcl);
}
