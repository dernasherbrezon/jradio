package ru.r2cloud.jradio.ctim;

public enum SunPointState {

	FINE_REF_POINT(0), SUN_POINT(1), UNKNOWN(255);

	private final int code;

	private SunPointState(int code) {
		this.code = code;
	}

	public static SunPointState valueOfCode(int code) {
		for (SunPointState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
