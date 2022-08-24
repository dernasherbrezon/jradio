package ru.r2cloud.jradio.cute;

public enum CcdShutter {

	CLOSED(0), OPEN(1), UNKNOWN(255);

	private final int code;

	private CcdShutter(int code) {
		this.code = code;
	}

	public static CcdShutter valueOfCode(int code) {
		for (CcdShutter cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
