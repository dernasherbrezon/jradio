package ru.r2cloud.jradio.skcube;

public enum AntennaStatus {
	
	DEFAULT(0), CLOSED(1), PARTIALLY_OPENED(2), OPENED(3);

	private final int code;

	private AntennaStatus(int code) {
		this.code = code;
	}

	public static AntennaStatus valueOfCode(int code) {
		for (AntennaStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
