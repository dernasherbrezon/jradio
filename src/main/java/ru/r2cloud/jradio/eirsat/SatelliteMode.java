package ru.r2cloud.jradio.eirsat;

public enum SatelliteMode {

	SEPARATION(0), COMMISSIONING(1), NOMINAL(2), WBC(3), SAFE(4), UNKNOWN(255);

	private final int code;

	private SatelliteMode(int code) {
		this.code = code;
	}

	public static SatelliteMode valueOfCode(int code) {
		for (SatelliteMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
