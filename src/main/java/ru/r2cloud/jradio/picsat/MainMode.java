package ru.r2cloud.jradio.picsat;

public enum MainMode {

	SBY(0), IDL(1), SCI(2), IMG(3), TST(4), UNKNOWN(255);

	private final int code;

	private MainMode(int code) {
		this.code = code;
	}

	public static MainMode valueOfCode(int code) {
		for (MainMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
