package ru.r2cloud.jradio.technosat;

import java.util.HashMap;
import java.util.Map;

public enum HighSpeedState {

	OFF(0), START(1), SENDING(2), PENDING(3);
	
	private final int code;
	private static final Map<Integer, HighSpeedState> typeByCode = new HashMap<>();

	static {
		for (HighSpeedState cur : HighSpeedState.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private HighSpeedState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static HighSpeedState valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
