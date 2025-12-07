package org.openautomaker.environment.preference;

public abstract class ASimpleIntegerPreference extends APreference<Integer> {

	@Override
	public Integer getValue() {
		return getNode().getInt(getKey(), getDefault());
	}

	@Override
	public void setValue(Integer value) {
		getNode().putInt(getKey(), value);

	}

	@Override
	public Integer getDefault() {
		return Integer.valueOf(0);
	}
}
