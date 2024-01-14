package ru.r2cloud.jradio.randev;

public enum MagnetometerMode {

	MAIN_SIGNAL(0), REDUNDANT_SIGNAL(1), MAIN_MOTOR(2), NONE(3), UNKNOWN(255);

	private final int code;

	private MagnetometerMode(int code) {
		this.code = code;
	}

	public static MagnetometerMode valueOfCode(int code) {
		for (MagnetometerMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
