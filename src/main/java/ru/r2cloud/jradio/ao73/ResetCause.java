package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum ResetCause {

	PowerOnReset(0), ExternalReset(1), BrownOutReset(2), WDTreset(3), JTAGreset(4), Otherreason(5);

	private final int code;
	private final static Map<Integer, ResetCause> typeByCode = new HashMap<>();

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
