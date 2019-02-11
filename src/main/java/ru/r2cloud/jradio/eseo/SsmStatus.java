package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum SsmStatus {

	OK(0b000), WDTRebootError(0b001), RTEMSError(0b010), StandBy(0b100);

	private final int code;
	private static final Map<Integer, SsmStatus> typeByCode = new HashMap<>();

	static {
		for (SsmStatus cur : SsmStatus.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private SsmStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SsmStatus valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
