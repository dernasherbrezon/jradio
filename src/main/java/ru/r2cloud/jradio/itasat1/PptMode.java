package ru.r2cloud.jradio.itasat1;

public enum PptMode {

	UNKNOWN(0), MPPT(1), FIXED(2);

	private final int code;

	private PptMode(int code) {
		this.code = code;
	}

	public static PptMode valueOfCode(int code) {
		for (PptMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return PptMode.UNKNOWN;
	}

}
