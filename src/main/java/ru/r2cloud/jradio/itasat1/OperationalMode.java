package ru.r2cloud.jradio.itasat1;

public enum OperationalMode {

	UNKNOWN(0), SAFE(1), NORMAL(2), AR(3), DCS(4), GPS(5), CAMERA(6);

	private final int code;

	private OperationalMode(int code) {
		this.code = code;
	}

	public static OperationalMode valueOfCode(int code) {
		for (OperationalMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return OperationalMode.UNKNOWN;
	}

}
