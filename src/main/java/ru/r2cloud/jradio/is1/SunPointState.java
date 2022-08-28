package ru.r2cloud.jradio.is1;

public enum SunPointState {

	UNDEF_0(0), UNDEF_1(1), SEARCH_INIT(2), SEARCHING(3), WAITING(4), CONVERGING(5), ON_SUN(6), NOT_ACTIVE(7), UNKNOWN(255);

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
