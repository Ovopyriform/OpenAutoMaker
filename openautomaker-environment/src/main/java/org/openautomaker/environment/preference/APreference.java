package org.openautomaker.environment.preference;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

/**
 * An abstract application preference
 * 
 * @param <T> - The type of preference
 */
public abstract class APreference<T> implements Preference<T> {

	private Preferences fUserNode = null;
	private Preferences fSystemNode = null;

	protected Preferences getUserNode() {
		if (fUserNode == null)
			fUserNode = Preferences.userNodeForPackage(getClass());

		return fUserNode;
	}

	protected Preferences getSystemNode() {
		if (fSystemNode == null)
			fSystemNode = Preferences.systemNodeForPackage(getClass());

		return fSystemNode;
	}

	protected String getKey() {
		return getClass().getSimpleName().replace("Preference", "");
	}

	@Override
	public void addChangeListener(PreferenceChangeListener pcl) {
		getNode().addPreferenceChangeListener(new PreferenceChangeListener() {
			@Override
			public void preferenceChange(PreferenceChangeEvent evt) {
				if (getKey().equals(evt.getKey()))
					pcl.preferenceChange(evt);
			}
		});
	}

	@Override
	public void remove() {
		getNode().remove(getKey());
	}


	@Override
	public abstract T getValue();

	@Override
	public abstract void setValue(T value);

	/**
	 * Return the user or system node this preference should be stored as.
	 * 
	 * @return Preferences - Either system node or user node
	 */
	protected abstract Preferences getNode();

}
