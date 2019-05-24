package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum SystemMode {
	
	PAUSE0(0), PAUSE1(1), LISTEN0(2), LISTEN1(3), TRANSCEIVE0(4), TRANSCEIVE1(5), BEACON0(6), BEACON1(7);

	private final int code;
	private static final Map<Integer, SystemMode> typeByCode = new HashMap<>();

	static {
		for (SystemMode cur : SystemMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private SystemMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SystemMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
