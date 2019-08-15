package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum OBDMode {

	OBDH_POWER_UP(0x00), AOCS_INITIALIZATION(0x01), AOCS_DAMPING(0x02), AOCS_NORMAL_SUN(0x04), AOCS_NORMAL_ECLIPSE(0x08), SAFE_MODE_S1(0x10), SAFE_MODE_S2(0x20), SAFE_MODE_S3(0x40);

	private final int code;
	private static final Map<Integer, OBDMode> typeByCode = new HashMap<>();

	static {
		for (OBDMode cur : OBDMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private OBDMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OBDMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
