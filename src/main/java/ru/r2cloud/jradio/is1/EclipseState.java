package ru.r2cloud.jradio.is1;

public enum EclipseState {

	SUN(0), ECLIPSE(1), UNKNOWN(255);

	private final int code;

	private EclipseState(int code) {
		this.code = code;
	}

	public static EclipseState valueOfCode(int code) {
		for (EclipseState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
