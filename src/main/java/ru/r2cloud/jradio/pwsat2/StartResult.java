package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum StartResult {

	Success(0), Failure(1);
	
	private final int code;
	private final static Map<Integer, StartResult> typeByCode = new HashMap<>();

	static {
		for (StartResult cur : StartResult.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private StartResult(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StartResult valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
