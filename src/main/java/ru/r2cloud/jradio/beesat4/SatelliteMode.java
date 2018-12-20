package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum SatelliteMode {
	
	Safe(0), Standby(1), LEOP(2), Active(3);

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
