package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum ExperimentType {
	DETUMBLING(1), ERASE_FLASH(2), SUNS(3), LEOP(4), RADFET(5), SADS(6), SAIL(7), FIBO(8), PAYLOAD(9), CAMERA(10);
	
	private final int code;
	private static final Map<Integer, ExperimentType> typeByCode = new HashMap<>();

	static {
		for (ExperimentType cur : ExperimentType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private ExperimentType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ExperimentType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
