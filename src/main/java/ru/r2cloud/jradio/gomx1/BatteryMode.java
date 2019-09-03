package ru.r2cloud.jradio.gomx1;

import java.util.HashMap;
import java.util.Map;

public enum BatteryMode {

	NORMAL(0), UNDERVOLTAGE(1), OVERVOLTAGE(2);
	
	private final int code;
	private static final Map<Integer, BatteryMode> typeByCode = new HashMap<>();

	static {
		for (BatteryMode cur : BatteryMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private BatteryMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static BatteryMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
