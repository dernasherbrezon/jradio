package ru.r2cloud.jradio.celesta;

public enum SatelliteMode {

	STANDBY(0), DEPLOY(1), COMMISSIONNING(2), COMM_PL(3), MISSION(4), LOW_P_MISSION(5), TRANSMIT(6), SURVIVAL(7), SILENT(8), UNKNOWN(255);

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
