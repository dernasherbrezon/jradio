package ru.r2cloud.jradio.at03;

import java.util.HashMap;
import java.util.Map;

public enum CCMode {

	BootMode(0b00), FlightMode(0b01), SafeMode(0b10), unavailable(0b11);

	private final int code;
	private final static Map<Integer, CCMode> typeByCode = new HashMap<>();

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
