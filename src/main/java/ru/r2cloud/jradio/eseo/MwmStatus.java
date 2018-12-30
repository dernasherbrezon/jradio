package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum MwmStatus {

	RunningBootLoader(0), RunningMemory1(1), RunningMemory2(2), FlashingMemory1(5), FlashingMemory2(6);

	private final int code;
	private final static Map<Integer, MwmStatus> typeByCode = new HashMap<>();

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
