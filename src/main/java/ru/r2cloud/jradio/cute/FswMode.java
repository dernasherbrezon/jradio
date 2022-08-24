package ru.r2cloud.jradio.cute;

public enum FswMode {

	STANDBY(0), SCIENCE(1), UNKNOWN(255);

	private final int code;

	private FswMode(int code) {
		this.code = code;
	}

	public static FswMode valueOfCode(int code) {
		for (FswMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
