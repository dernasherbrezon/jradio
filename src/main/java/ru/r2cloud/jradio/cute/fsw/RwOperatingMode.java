package ru.r2cloud.jradio.cute.fsw;

public enum RwOperatingMode {

	RAW(1), FIXED_GAIN_NO_BIAS(2), FIXED_GAIN(3), MEKF(4), UNKNOWN(255);

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
