package ru.r2cloud.jradio.suchai2;

public enum OpMode {

	DEPLOYING(0), NORMAL(1), WARN(2), FAIL(3), REF_POINT(4), NAD_POINT(5), DETUMB_MAG(6), UNKNOWN(255);

	private final int code;

	private OpMode(int code) {
		this.code = code;
	}

	public static OpMode valueOfCode(int code) {
		for (OpMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
