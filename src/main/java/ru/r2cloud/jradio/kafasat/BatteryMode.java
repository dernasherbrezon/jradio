package ru.r2cloud.jradio.kafasat;

public enum BatteryMode {

	CRITICAL(1), SAFE(2), NORMAL(3), UNKNOWN(255);

	private final int code;

	private BatteryMode(int code) {
		this.code = code;
	}

	public static BatteryMode valueOfCode(int code) {
		for (BatteryMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
