package ru.r2cloud.jradio.sharjahsat;

public enum OpMode {

	STARTUP(0), NOMINAL(1), SAFE(2), ADCS_CALIBRATION(3), RECOVERY(4), SUN_POINTING(5), CAMERA_OPERATION(6), XRD_OPERATION(7), DIAGNOSTICS(8), UNKNOWN(255);

	private final int code;

	private OpMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OpMode valueOfCode(int code) {
		for (OpMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
