package ru.r2cloud.jradio.randev;

public enum EstimationMode {

	DISABLED(0), MEMS_RATE(1), MAGNETOMETER_FILTER(2), MAGNETOMETER_FILTER_PITCH(3), MAGNETOMETER_FINE_SUN_TRIAD(4), FULL_EKF(5), MEMS_GYRO_EKF(6), UNKNOWN(255);

	private final int code;

	private EstimationMode(int code) {
		this.code = code;
	}

	public static EstimationMode valueOfCode(int code) {
		for (EstimationMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
