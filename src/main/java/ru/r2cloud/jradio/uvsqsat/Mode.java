package ru.r2cloud.jradio.uvsqsat;

public enum Mode {

	STARTUP(0), NOMINAL(1), SAFETY(2), EMERGENCY_LOW_POWER(3);

	private final int code;

	private Mode(int code) {
		this.code = code;
	}

	public static Mode valueOfCode(int code) {
		for (Mode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
