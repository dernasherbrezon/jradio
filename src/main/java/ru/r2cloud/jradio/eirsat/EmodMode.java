package ru.r2cloud.jradio.eirsat;

public enum EmodMode {

	IDLE(1), EXPERIMENT(2), OFF(3), UNKNOWN(255);

	private final int code;

	private EmodMode(int code) {
		this.code = code;
	}

	public static EmodMode valueOfCode(int code) {
		for (EmodMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
