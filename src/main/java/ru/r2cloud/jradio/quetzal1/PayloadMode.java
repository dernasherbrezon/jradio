package ru.r2cloud.jradio.quetzal1;

public enum PayloadMode {

	BASIC, FULL, UNKNOWN;

	public static PayloadMode valueOfCode(int code) {
		if (code == 0x42) {
			return BASIC;
		} else if (code == 0x46) {
			return FULL;
		} else {
			return UNKNOWN;
		}
	}

}
