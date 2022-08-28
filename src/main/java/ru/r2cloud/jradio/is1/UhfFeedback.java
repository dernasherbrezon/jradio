package ru.r2cloud.jradio.is1;

public enum UhfFeedback {

	N(0), B(1), T(2), UNKNOWN(255);

	private final int code;

	private UhfFeedback(int code) {
		this.code = code;
	}

	public static UhfFeedback valueOfCode(int code) {
		for (UhfFeedback cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
