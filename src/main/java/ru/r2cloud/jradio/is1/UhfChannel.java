package ru.r2cloud.jradio.is1;

public enum UhfChannel {

	A(0), B(1), D(2), UNKNOWN(255);

	private final int code;

	private UhfChannel(int code) {
		this.code = code;
	}

	public static UhfChannel valueOfCode(int code) {
		for (UhfChannel cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
