package ru.r2cloud.jradio.cute;

public enum SunPointState {

	SUN_POINT(0), FINE_REF_POINT(1), SEARCH_INIT(2), SEARCHING(3), WAITING(4), CONVERGING(5), ON_SUN(6), NOT_ACTIVE(7), UNKNOWN(255);

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
