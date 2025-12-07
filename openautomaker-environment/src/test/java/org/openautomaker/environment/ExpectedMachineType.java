package org.openautomaker.environment;

public enum ExpectedMachineType {
	//os.arch: x86_64 || aarch64
	//os.name: Mac OS X
	//
	WINDOWS("^Windows.*"),

	LINUX("^Linux.*"),

	MAC("^Mac.*");

	String regex;

	private ExpectedMachineType(String regex) {
		this.regex = regex;
	}

	String getRegex() {
		return this.regex;
	}
}
