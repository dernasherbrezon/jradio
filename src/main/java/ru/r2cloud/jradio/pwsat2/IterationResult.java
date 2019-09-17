package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum IterationResult {

	NO_RESULT(0), FINISHED(1), LOOP_IMMEDIATELY(2), WAIT_FOR_NEXT_CYCLE(3), FAILURE(4);

	private final int code;
	private static final Map<Integer, IterationResult> typeByCode = new HashMap<>();

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
