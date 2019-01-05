package ru.r2cloud.jradio.gomx1;

import java.util.HashMap;
import java.util.Map;

public enum BatteryMode {

	Normal(0), Undervoltage(1), Overvoltage(2);
	
	private final int code;
	private final static Map<Integer, BatteryMode> typeByCode = new HashMap<>();

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
