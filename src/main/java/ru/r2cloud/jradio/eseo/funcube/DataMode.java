package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum DataMode {

	Mode1k2(0x00), PayloadMode4k8(0x01);

	private final int code;
	private final static Map<Integer, DataMode> typeByCode = new HashMap<>();

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
