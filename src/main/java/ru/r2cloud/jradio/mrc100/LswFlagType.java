package ru.r2cloud.jradio.mrc100;

public enum LswFlagType {

	ON(1), OFF(0), FAIL(-1), OVERCURRENT(-2), OVERVOLTAGE(-3), UNKNOWN(255);

	private final int code;

	private LswFlagType(int code) {
		this.code = code;
	}

	public static LswFlagType valueOfCode(int code) {
		for (LswFlagType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
