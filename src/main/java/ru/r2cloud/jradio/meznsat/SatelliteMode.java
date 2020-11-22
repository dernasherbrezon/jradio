package ru.r2cloud.jradio.meznsat;

public enum SatelliteMode {

	INITIAL_CHECKOUT(1), NORMAL(2), GROUND_CONTACT(3), PAYLOAD(4), SAFE(5);

	private int code;

	private SatelliteMode(int code) {
		this.code = code;
	}

	public static SatelliteMode valueOfCode(int code) {
		for (SatelliteMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
