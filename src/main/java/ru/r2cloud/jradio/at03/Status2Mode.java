package ru.r2cloud.jradio.at03;

import java.util.HashMap;
import java.util.Map;

public enum Status2Mode {

	DEBUG(0b000), BOOT(0b001), FLIGHT(0b010), POWER_DOWN(0b011), SAFE(0b100);

	private final int code;
	private static final Map<Integer, Status2Mode> typeByCode = new HashMap<>();

	static {
		for (Status2Mode cur : Status2Mode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private Status2Mode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Status2Mode valueOfCode(int code) {
		return typeByCode.get(code);
	}

}
