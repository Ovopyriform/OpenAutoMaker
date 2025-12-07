package org.openautomaker.base.configuration.fileRepresentation;

public enum SupportType {
    /**
     * Use material 1 as support. Implies all objects printed with material 2.
     */
    MATERIAL_1("supportType.material1", 0),
    /**
     * Use material 2 as support. Implies all objects printed with material 1.
     */
    MATERIAL_2("supportType.material2", 1),
    /**
     * Use the material options specified in the profile.
     */
    AS_PROFILE("supportType.profile", 0);

	private final String key;
    private final int extruderNumber;

	SupportType(String key, int extruderNumber) {
		this.key = key;
        this.extruderNumber = extruderNumber;
    }

	public String getKey() {
		return key;
    }

    public int getExtruderNumber() {
        return extruderNumber;
    }
}
