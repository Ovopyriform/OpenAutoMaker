package org.openautomaker.environment.preference;

/**
 * Represents an application preference which has a static application value and a modifiable user value
 * 
 * @param <T> The type of parameter this represents
 */
public interface PairedPreference<T> extends Preference<T> {

	/**
	 * Return the user value of this preference
	 * 
	 * @return returns the value from getValue
	 */
	public T getUserValue();

	/**
	 * Returns the application value for this preference. Generally this is static for the application.
	 * 
	 * @return The Application value for this preference.
	 */
	public T getAppValue();
}
