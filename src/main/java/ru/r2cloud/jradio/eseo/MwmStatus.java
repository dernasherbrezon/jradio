package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum MwmStatus {

	RUNNING_BOOT_LOADER(0), RUNNING_MEMORY1(1), RUNNING_MEMORY2(2), FLASHING_MEMORY1(5), FLASHING_MEMORY2(6);

	private final int code;
	private static final Map<Integer, MwmStatus> typeByCode = new HashMap<>();

	static {
		for (MwmStatus cur : MwmStatus.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private MwmStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MwmStatus valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
