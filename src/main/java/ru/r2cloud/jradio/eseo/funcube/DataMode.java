package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum DataMode {

	MODE_1K2(0x00), PAYLOAD_MODE_4K8(0x01);

	private final int code;
	private static final Map<Integer, DataMode> typeByCode = new HashMap<>();

	static {
		for (DataMode cur : DataMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private DataMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DataMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
