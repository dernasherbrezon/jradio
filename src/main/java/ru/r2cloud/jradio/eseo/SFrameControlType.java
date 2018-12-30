package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum SFrameControlType {

	RR(0b0000_0001), RNR(0b0000_0101), REJ(0b0000_1001), SREJ(0b0000_1101);

	private final int code;
	private final static Map<Integer, SFrameControlType> typeByCode = new HashMap<>();

	static {
		for (SFrameControlType cur : SFrameControlType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private SFrameControlType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SFrameControlType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
