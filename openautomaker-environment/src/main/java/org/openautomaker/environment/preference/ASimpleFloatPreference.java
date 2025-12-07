package org.openautomaker.environment.preference;

/**
 * Abstract float application preference
 */
public abstract class ASimpleFloatPreference extends APreference<Float> {

	@Override
	public Float getValue() {
		return getNode().getFloat(getKey(), getDefault());
	}

	@Override
	public void setValue(Float value) {
		getNode().putFloat(getKey(), value);
	}

	@Override
	public Float getDefault() {
		return Float.valueOf(0.0f);
	}
}
