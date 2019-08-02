package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum SunMode {

	ECLIPSE(0), SUN(1);
	
	private final int code;
	private static final Map<Integer, SunMode> typeByCode = new HashMap<>();

	static {
		for (SunMode cur : SunMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private SunMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SunMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
