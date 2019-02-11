package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum RFMode {
	
	Receiveonly(0x00), LowPowerBPSK(0x01), HighPowerBPSK(0x02), LowPowerTransponder(0x03), HighPowerTransponder(0x04), Autonomous(0x05);

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
