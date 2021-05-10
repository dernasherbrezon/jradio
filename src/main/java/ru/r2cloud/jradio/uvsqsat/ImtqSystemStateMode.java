package ru.r2cloud.jradio.uvsqsat;

public enum ImtqSystemStateMode {

	IDLE(0), SELFTEST(1), DETUMBLE(2);

	private final int code;

	private ImtqSystemStateMode(int code) {
		this.code = code;
	}

	public static ImtqSystemStateMode valueOfCode(int code) {
		for (ImtqSystemStateMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
