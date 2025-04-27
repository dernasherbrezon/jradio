package ru.r2cloud.jradio.snuglite;

public enum OperationMode {

	TLE(0), GPS(1), UNKNOWN(255);

	private final int code;

	private OperationMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OperationMode valufOfCode(int code) {
		for (OperationMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
