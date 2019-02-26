package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum AcsErrorCode {

	UNKNOWN_MODE(1), WHEEL_SPEED(2), UNKNOWN_COMMAND(7), TOO_MANY_SUN_VECS(20), WRONG_SUN_SENSOR(22), TOO_FEW_SUN_VECS(23), WRONG_WHEEL_NUMBER(24);

	private final int code;
	private static final Map<Integer, AcsErrorCode> typeByCode = new HashMap<>();

	static {
		for (AcsErrorCode cur : AcsErrorCode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private AcsErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AcsErrorCode valueOfCode(int code) {
		return typeByCode.get(code);
	}

}
