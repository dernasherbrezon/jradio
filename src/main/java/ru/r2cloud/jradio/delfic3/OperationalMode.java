package ru.r2cloud.jradio.delfic3;

public enum OperationalMode {

	IDLE(0), DEPLOYMENT(1), BASIC(2), SCIENCE(3), TRANSPONDER(4);

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
		return null;
	}

}
