package ru.r2cloud.jradio.beesat;

import java.util.HashMap;
import java.util.Map;

public enum HistoryBufferType {

	BUS(0), PLD(1), DIAG(2), none(3);
	
	private final int code;
	private final static Map<Integer, HistoryBufferType> typeByCode = new HashMap<>();

	static {
		for (HistoryBufferType cur : HistoryBufferType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private HistoryBufferType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static HistoryBufferType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
