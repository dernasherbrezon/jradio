package ru.r2cloud.jradio.cute;

public enum SunVectorStatus {

	GOOD(0), COARSE(1), BAD(2), UNKNOWN(255);

	private final int code;

	private SunVectorStatus(int code) {
		this.code = code;
	}

	public static SunVectorStatus valueOfCode(int code) {
		for (SunVectorStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
