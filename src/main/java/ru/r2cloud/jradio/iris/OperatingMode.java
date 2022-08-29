package ru.r2cloud.jradio.iris;

public enum OperatingMode {

	SAFE(0), INIT(1), NOMINAL(2), RECOVERY(3), UNKNOWN(255);

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
