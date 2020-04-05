package ru.r2cloud.jradio.qarman;

public enum AttitudeEstimationMode {

	NO_ATTITUDE_ESTIMATION(0), MEMS_RATE_SENSING(1), MAGNETOMETER_RATE_FILTER(2), MAGNETOMETER_RATE_FILTER_WITH_PITCH_ESTIMATION(3), FULL_STATE_EKF(4), MAGNETOMETER_AND_FINE_SUN_TRIAD_ALGORITHM(5);

	private final int code;

	private AttitudeEstimationMode(int code) {
		this.code = code;
	}

	public static AttitudeEstimationMode valueOfCode(int code) {
		for (AttitudeEstimationMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
