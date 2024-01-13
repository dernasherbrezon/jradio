package ru.r2cloud.jradio.bdsat;

public enum SystemState {

	OK("1"), POWER_SAVING("2"), POWER_CRITICAL("3"), UNKNOWN("255");

	private final String code;

	private SystemState(String code) {
		this.code = code;
	}

	public static SystemState valueOfCode(String code) {
		for (SystemState cur : values()) {
			if (cur.code.equalsIgnoreCase(code)) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
