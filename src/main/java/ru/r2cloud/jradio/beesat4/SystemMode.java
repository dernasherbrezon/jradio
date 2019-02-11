package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum SystemMode {
	
	Pause0(0), Pause1(1), Listen0(2), Listen1(3), Transceive0(4), Transceive1(5), Beacon0(6), Beacon1(7);

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
