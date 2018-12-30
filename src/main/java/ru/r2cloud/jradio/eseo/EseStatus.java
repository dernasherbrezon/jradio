package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum EseStatus {

	OK(0b000), WDTRebootError(0b001), RTEMSError(0b010);

	private final int code;
	private final static Map<Integer, EseStatus> typeByCode = new HashMap<>();

	static {
		for (EseStatus cur : EseStatus.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private EseStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static EseStatus valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
