package ru.r2cloud.jradio.at03;

import java.util.HashMap;
import java.util.Map;

public enum StacieOperationalMode {

	NORMAL(0), SLEEP(2), BEACON(3), DEPLOYMENT(4), SHUTDOWN(8);
	
	private final int code;
	private static final Map<Integer, StacieOperationalMode> typeByCode = new HashMap<>();

	static {
		for (StacieOperationalMode cur : StacieOperationalMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private StacieOperationalMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StacieOperationalMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
