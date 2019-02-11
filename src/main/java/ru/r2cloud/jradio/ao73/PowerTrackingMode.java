package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum PowerTrackingMode {
	
	HARDWARE(0), MPPT(1), FIXED_SWPPT(2);

	private final int code;
	private static final Map<Integer, PowerTrackingMode> typeByCode = new HashMap<>();

	static {
		for (PowerTrackingMode cur : PowerTrackingMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private PowerTrackingMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PowerTrackingMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
