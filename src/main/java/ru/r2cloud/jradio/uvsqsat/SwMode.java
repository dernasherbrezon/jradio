package ru.r2cloud.jradio.uvsqsat;

public enum SwMode {

	MODE_INIT(0), MODE_DETUMBLING(1), MODE_STANDBY(2), MODE_OPERATIONAL(3), MODE_SAFE(4), MODE_TRANSPONDER(5);

	private final int code;

	private SwMode(int code) {
		this.code = code;
	}

	public static SwMode valueOfCode(int code) {
		for (SwMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
