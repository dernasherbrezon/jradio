package ru.r2cloud.jradio.qarman;

public enum ReasonForModeChange {

	SYSTEM_RESET(0), COMMAND(1), TIMEOUT(2), UNDER_VOLTAGE(3), ACCELEROMETER(4), IMU(5), GPS(6), THERMOCOUPLE(7);

	private final int code;

	private ReasonForModeChange(int code) {
		this.code = code;
	}

	public static ReasonForModeChange valueOfCode(int code) {
		for (ReasonForModeChange cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
