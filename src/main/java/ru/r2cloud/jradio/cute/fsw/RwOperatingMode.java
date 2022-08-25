package ru.r2cloud.jradio.cute.fsw;

public enum RwOperatingMode {

	IDLE(0), INT(1), EXT(2), UNKNOWN(255);

	private final int code;

	private RwOperatingMode(int code) {
		this.code = code;
	}

	public static RwOperatingMode valueOfCode(int code) {
		for (RwOperatingMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
