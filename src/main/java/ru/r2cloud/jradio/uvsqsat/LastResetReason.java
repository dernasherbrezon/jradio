package ru.r2cloud.jradio.uvsqsat;

public enum LastResetReason {

	FIRST_START(0), TC_INIT_RECEIVED(0x80), NO_TC_SINCE_4_DAYS(0x81), UNKNOWN_REASON(0x81);

	private final int code;

	private LastResetReason(int code) {
		this.code = code;
	}

	public static LastResetReason valueOfCode(int code) {
		for (LastResetReason cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
