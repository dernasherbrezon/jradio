package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum ObdMode {

	EGSE(0x00), OBDH_POWER_UP(0x01), AOCS_INITIALIZATION(0x02), AOCS_DAMPING(0x03), AOCS_NOMINAL(0x04), REENTRY(0xF0), SAFE_MODE_S1(0xF1), SAFE_MODE_S2(0xF2), SAFE_MODE_S3(0xF3), SAFE_MODE_S4(0xF4);

	private final int code;
	private static final Map<Integer, ObdMode> typeByCode = new HashMap<>();

	static {
		for (ObdMode cur : ObdMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private ObdMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ObdMode valueOfCode(int code) {
		return typeByCode.get(code);
	}

}
