package ru.r2cloud.jradio.technosat;

import java.util.HashMap;
import java.util.Map;

public enum AcsMode {

	DIAGNOSIS(0), MANUAL(1), SUSPEND(2), DAMPING_BDOT(3), DAMPING_CROSS(4), NADIR_COARSE(5), NADIR_FINE(6), INERTIAL_COARSE(7), INERTIAL_FINE(8), RATE_CONTROL(9);

	private final int code;
	private final static Map<Integer, AcsMode> typeByCode = new HashMap<>();

	static {
		for (AcsMode cur : AcsMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private AcsMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AcsMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
