package ru.r2cloud.jradio.ctim;

public enum AdcsAlive {

	OFF(0), DEAD(1), ALIVE(2), UNKNOWN(255);

	private final int code;

	private AdcsAlive(int code) {
		this.code = code;
	}

	public static AdcsAlive valueOfCode(int code) {
		for (AdcsAlive cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
