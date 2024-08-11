package ru.r2cloud.jradio.catsat;

public enum MpttMode {

	TRACKING(1), FIXED(2), UNKNOWN(255);

	private final int code;

	private MpttMode(int code) {
		this.code = code;
	}

	public static MpttMode valueOfCode(int code) {
		for (MpttMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
