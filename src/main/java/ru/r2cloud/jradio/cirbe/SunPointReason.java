package ru.r2cloud.jradio.cirbe;

public enum SunPointReason {

	BOOT(0), COMMAND(1), ATTITUDE_INVALID(2), TIME_INVALID(3), REFS_INVALID(4), UNKNOWN(127);

	private final int code;

	private SunPointReason(int code) {
		this.code = code;
	}

	public static SunPointReason valueOfCode(int code) {
		for (SunPointReason cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
