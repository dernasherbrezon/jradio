package ru.r2cloud.jradio.eirsat;

public enum StreamStatus {

	DISABLED(0), ENABLED(1), OFF(3), UNKNOWN(255);

	private final int code;

	private StreamStatus(int code) {
		this.code = code;
	}

	public static StreamStatus valueOfCode(int code) {
		for (StreamStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
