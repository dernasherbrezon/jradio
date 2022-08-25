package ru.r2cloud.jradio.cute.fsw;

public enum RwControlMode {

	TRQ(0), SPD(1), PWM(2), ISOLATE(3), UNKNOWN(255);

	private final int code;

	private RwControlMode(int code) {
		this.code = code;
	}

	public static RwControlMode valueOfCode(int code) {
		for (RwControlMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
