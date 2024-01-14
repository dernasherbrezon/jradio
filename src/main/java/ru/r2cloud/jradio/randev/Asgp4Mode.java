package ru.r2cloud.jradio.randev;

public enum Asgp4Mode {

	DISABLED(0), TRIGGER(1), BACKGROUND(2), AUGMENT(3), UNKNOWN(255);

	private final int code;

	private Asgp4Mode(int code) {
		this.code = code;
	}

	public static Asgp4Mode valueOfCode(int code) {
		for (Asgp4Mode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
