package org.openautomaker.environment.preference;

/**
 * Abstract string based application preference
 */
public abstract class ASimpleStringPreference extends APreference<String> {

	public ASimpleStringPreference() {
		super();
	}

	@Override
	public String getValue() {
		return getNode().get(getKey(), getDefault());
	}

	@Override
	public void setValue(String value) {
		getNode().put(getKey(), value);
	}

	@Override
	public String getDefault() {
		return "";
	}
}
