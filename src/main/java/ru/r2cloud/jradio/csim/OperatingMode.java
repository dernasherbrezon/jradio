package ru.r2cloud.jradio.csim;

public enum OperatingMode {

	IDLE(0), INT(1), EXT(2), UNKNOWN(255);

	private final int code;

	private OperatingMode(int code) {
		this.code = code;
	}

	public static OperatingMode valueOfCode(int code) {
		for (OperatingMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
