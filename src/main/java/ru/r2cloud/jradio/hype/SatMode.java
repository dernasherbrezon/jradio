package ru.r2cloud.jradio.hype;

public enum SatMode {

	SAFE(0x00), NORMAL(0x01), PAYLOAD(0x02), UNKNOWN(0xFF);

	private final int code;

	private SatMode(int code) {
		this.code = code;
	}

	public static SatMode valueOfCode(int code) {
		for (SatMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
