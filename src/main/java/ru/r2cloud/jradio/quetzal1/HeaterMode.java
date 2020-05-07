package ru.r2cloud.jradio.quetzal1;

public enum HeaterMode {

	AUTONOMOUS, MANUAL, UNKNOWN;

	public static HeaterMode valueOfCode(int code) {
		if (code == 0b1001) {
			return AUTONOMOUS;
		} else if (code == 0b0110) {
			return MANUAL;
		} else {
			return UNKNOWN;
		}
	}
}
