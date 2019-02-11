package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum ExperimentType {
	Detumbling(1), EraseFlash(2), SunS(3), LEOP(4), RadFET(5), SADS(6), Sail(7), Fibo(8), Payload(9), Camera(10);
	
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
