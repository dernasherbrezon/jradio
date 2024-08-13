package ru.r2cloud.jradio.eirsat;

public enum GmodMode {

	IDLE(1), EXPERIMENT(2), CPLD_REPROGRAM(3), SAFE(4), EXPERIMENT_16(5), OFF(15), UNKNOWN(255);

	private final int code;

	private GmodMode(int code) {
		this.code = code;
	}

	public static GmodMode valueOfCode(int code) {
		for (GmodMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
