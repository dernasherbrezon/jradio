package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum ResetCause {

	POWER_ON_RESET(0), EXTERNAL_RESET(1), BROWN_OUT_RESET(2), WDT_RESET(3), JTAG_RESET(4), OTHER_REASON(5);

	private final int code;
	private static final Map<Integer, ResetCause> typeByCode = new HashMap<>();

	static {
		for (ResetCause cur : ResetCause.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private ResetCause(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ResetCause valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
