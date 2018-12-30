package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum AcsState {

	Safe(1), Nominal(2);

	private final int code;
	private final static Map<Integer, AcsState> typeByCode = new HashMap<>();

	static {
		for (AcsState cur : AcsState.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private AcsState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AcsState valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
