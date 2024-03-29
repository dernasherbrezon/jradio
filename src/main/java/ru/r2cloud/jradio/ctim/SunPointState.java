package ru.r2cloud.jradio.ctim;

public enum SunPointState {

	SEARCHING(3), WAITING(4), CONVERGING(5), NOT_ACTIVE(7), ON_SUN(6), SEARCH_INIT(2), UNKNOWN(255);

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
