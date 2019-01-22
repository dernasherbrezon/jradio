package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum IterationResult {

	NoResult(0), Finished(1), LoopImmediately(2), WaitForNextCycle(3), Failure(4);

	private final int code;
	private final static Map<Integer, IterationResult> typeByCode = new HashMap<>();

	static {
		for (IterationResult cur : IterationResult.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private IterationResult(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static IterationResult valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
