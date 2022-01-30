package ru.r2cloud.jradio.delfipq;

public enum Service {

	BEACON(80), PING(17), TELEMETRY(3);

	private final int code;

	private Service(int code) {
		this.code = code;
	}

	public static Service valueOfCode(int code) {
		for (Service cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
