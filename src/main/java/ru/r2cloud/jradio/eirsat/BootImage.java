package ru.r2cloud.jradio.eirsat;

public enum BootImage {

	FAILSAFE(0), PRIMARY1(1), PRIMARY2(2), UNKNOWN(255);

	private final int code;

	private BootImage(int code) {
		this.code = code;
	}

	public static BootImage valueOfCode(int code) {
		for (BootImage cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
