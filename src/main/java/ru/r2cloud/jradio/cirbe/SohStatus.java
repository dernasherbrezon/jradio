package ru.r2cloud.jradio.cirbe;

public enum SohStatus {

	OK(0), FAIL(-1), IN_PROG(1), ABORTED(-2), UNKNOWN(127);

	private final int code;

	private SohStatus(int code) {
		this.code = code;
	}

	public static SohStatus valueOfCode(int code) {
		for (SohStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
