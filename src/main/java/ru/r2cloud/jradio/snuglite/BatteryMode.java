package ru.r2cloud.jradio.snuglite;

public enum BatteryMode {

	INITIAL(0), UNDERVOLTAGE(1), SAFEMODE(2), NOMINAL(3), FULL(4), UNKNOWN(255);

	private final int code;

	private BatteryMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static BatteryMode valufOfCode(int code) {
		for (BatteryMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
