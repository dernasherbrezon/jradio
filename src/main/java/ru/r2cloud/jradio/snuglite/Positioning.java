package ru.r2cloud.jradio.snuglite;

public enum Positioning {

	TLE(0), GPS(1), UNKNOWN(255);

	private final int code;

	private Positioning(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Positioning valufOfCode(int code) {
		for (Positioning cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
