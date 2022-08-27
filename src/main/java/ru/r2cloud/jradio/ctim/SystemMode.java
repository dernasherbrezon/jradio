package ru.r2cloud.jradio.ctim;

public enum SystemMode {

	PHOENIX(0), SAFE(1), NOMINAL(2), UNKNOWN(255);

	private final int code;

	private SystemMode(int code) {
		this.code = code;
	}

	public static SystemMode valueOfCode(int code) {
		for (SystemMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
