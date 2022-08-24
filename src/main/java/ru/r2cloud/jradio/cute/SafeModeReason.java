package ru.r2cloud.jradio.cute;

public enum SafeModeReason {

	BOOT(1), COMMAND(2), ATTITUDE_INVALID(4), TIME_INVALID(8), ATTITUDE_TIME_INVALID(12), REFS_INVALID(16), UNKNOWN(255);

	private final int code;

	private SafeModeReason(int code) {
		this.code = code;
	}

	public static SafeModeReason valueOfCode(int code) {
		for (SafeModeReason cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
