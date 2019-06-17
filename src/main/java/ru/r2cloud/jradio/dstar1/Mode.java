package ru.r2cloud.jradio.dstar1;

import java.util.HashMap;
import java.util.Map;

public enum Mode {
	
	SAFE(0x01), NOMINAL(0x02), EXPERIMENTAL(0x03);

	private final int code;
	private static final Map<Integer, Mode> typeByCode = new HashMap<>();

	static {
		for (Mode cur : Mode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private Mode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Mode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
