package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum RFMode {
	
	RECEIVE_ONLY(0x00), LOW_POWER_BPSK(0x01), HIGH_POWER_BPSK(0x02), LOW_POWER_TRANSPONDER(0x03), HIGH_POWER_TRANSPONDER(0x04), AUTONOMOUS(0x05);

	private final int code;
	private static final Map<Integer, RFMode> typeByCode = new HashMap<>();

	static {
		for (RFMode cur : RFMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private RFMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RFMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
