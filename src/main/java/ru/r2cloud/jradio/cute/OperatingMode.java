package ru.r2cloud.jradio.cute;

public enum OperatingMode {

	IDLE(0), INITIALIZE(1), STARID(2), TRACK(3), PHOTO(4), CAL(5), UNKNOWN(255);

	private final int code;

	private OperatingMode(int code) {
		this.code = code;
	}

	public static OperatingMode valueOfCode(int code) {
		for (OperatingMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
