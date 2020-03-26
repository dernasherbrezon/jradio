package ru.r2cloud.jradio.itasat1;

public enum BatteryOperationalMode {

	UNKNOWN(0), INITIAL(1), UNDER_VOLTAGE(2), NOMINAL(3), BATTERY_FULL(4);

	private final int code;

	private BatteryOperationalMode(int code) {
		this.code = code;
	}

	public static BatteryOperationalMode valueOfCode(int code) {
		for (BatteryOperationalMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return BatteryOperationalMode.UNKNOWN;
	}

}
