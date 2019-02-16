package ru.r2cloud.jradio.rhw;

import java.util.HashMap;
import java.util.Map;

public enum RadioPacketType {
	
	CSP(1), RELAY(2);

	private final int code;
	private static final Map<Integer, RadioPacketType> typeByCode = new HashMap<>();

	static {
		for (RadioPacketType cur : RadioPacketType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private RadioPacketType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RadioPacketType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
