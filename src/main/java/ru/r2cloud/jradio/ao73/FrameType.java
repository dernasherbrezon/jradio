package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum FrameType {

	WO1(0), WO2(1), WO3(2), WO4(3), WO5(4), WO6(5), WO7(6), WO8(7), WO9(8), WO10(9), WO11(10), WO12(11), HR1(12), FM1(13), FM2(14), FM3(15), HR2(16), FM4(17), FM5(18), FM6(19), HR3(20), FM7(21), FM8(22), FM9(23);

	private final int code;
	private final static Map<Integer, FrameType> typeByCode = new HashMap<>();

	static {
		for (FrameType cur : FrameType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private FrameType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FrameType valueOfCode(int code) {
		return typeByCode.get(code);
	}

	public boolean isWholeOrbit() {
		return code <= 12;
	}

}
