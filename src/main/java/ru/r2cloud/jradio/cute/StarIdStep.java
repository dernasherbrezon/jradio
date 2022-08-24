package ru.r2cloud.jradio.cute;

public enum StarIdStep {

	IDLE(0), INITIALIZE(1), WAITING_FOR_IMAGE1(2), WAITING_FOR_IMAGE2(3), CALCULATE_RATE(4), MAKE_UNIT_VECTORS(5), AWAITING_TRISTAR(6), OK_FOUND_4(7), OK_FOUND_3(8), TIME_OUT(9), SPARE(10), NO_MATCH(11), UNKNOWN(255);

	private final int code;

	private StarIdStep(int code) {
		this.code = code;
	}

	public static StarIdStep valueOfCode(int code) {
		for (StarIdStep cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
