package ru.r2cloud.jradio.is1;

public enum SystemMode {

	PHOENIX(0), SAFE(1), SCID(2), SCIC(3), UNKNOWN(255);

	private final int code;

	private SystemMode(int code) {
		this.code = code;
	}

	public static SystemMode valueOfCode(int code) {
		for (SystemMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
