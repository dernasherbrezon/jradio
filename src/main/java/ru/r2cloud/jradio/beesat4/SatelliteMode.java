package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum SatelliteMode {
	
	SAFE(0), STANDBY(1), LEOP(2), ACTIVE(3);

	private final int code;
	private static final Map<Integer, SatelliteMode> typeByCode = new HashMap<>();

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
