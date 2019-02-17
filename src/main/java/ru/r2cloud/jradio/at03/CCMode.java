package ru.r2cloud.jradio.at03;

import java.util.HashMap;
import java.util.Map;

public enum CCMode {

	BOOT_MODE(0b00), FLIGHT_MODE(0b01), SAFE_MODE(0b10), UNAVAILABLE(0b11);

	private final int code;
	private static final Map<Integer, CCMode> typeByCode = new HashMap<>();

	static {
		for (CCMode cur : CCMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private CCMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static CCMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
