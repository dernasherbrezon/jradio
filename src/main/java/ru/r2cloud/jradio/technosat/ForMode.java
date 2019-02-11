package ru.r2cloud.jradio.technosat;

import java.util.HashMap;
import java.util.Map;

public enum ForMode {
	
	TEST(0), PUBLISH_TO_AOCS(1), HISTORY_ONLY(2);

	private final int code;
	private static final Map<Integer, ForMode> typeByCode = new HashMap<>();

	static {
		for (ForMode cur : ForMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private ForMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ForMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
