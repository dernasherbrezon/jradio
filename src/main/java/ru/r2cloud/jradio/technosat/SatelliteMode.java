package ru.r2cloud.jradio.technosat;

import java.util.HashMap;
import java.util.Map;

public enum SatelliteMode {
	
	SAFE(0), LEOP(1), NOMINAL(2);

	private final int code;
	private final static Map<Integer, SatelliteMode> typeByCode = new HashMap<>();

	static {
		for (SatelliteMode cur : SatelliteMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private SatelliteMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SatelliteMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
