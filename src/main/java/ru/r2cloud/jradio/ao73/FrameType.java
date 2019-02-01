package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum FrameType {

	WO1(1), WO2(2), WO3(3), WO4(4), WO5(5), WO6(6), WO7(7), WO8(8), WO9(9), WO10(10), WO11(11), WO12(12), HR1(13), FM1(14), FM2(15), FM3(16), HR2(17), FM4(18), FM5(19), FM6(20), HR3(21), FM7(22), FM8(23), FM9(24);

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
		return name().startsWith("WO");
	}

	public boolean isHighResolutionData() {
		return name().startsWith("HR");
	}

	public boolean isFitterMessage() {
		return name().startsWith("FM");
	}

	public int getIndex() {
		if (isWholeOrbit()) {
			return code;
		}
		if (isHighResolutionData()) {
			switch (code) {
			case 13:
				return 1;
			case 17:
				return 2;
			case 21:
				return 3;
			default:
				throw new IllegalArgumentException("invalid code: " + code);
			}
		}
		if( isFitterMessage() ) {
			switch (code) {
			case 14:
				return 1;
			case 15:
				return 2;
			case 16:
				return 3;
			case 18:
				return 4;
			case 19:
				return 5;
			case 20:
				return 6;
			case 22:
				return 7;
			case 23:
				return 8;
			case 24:
				return 9;
			default:
				throw new IllegalArgumentException("invalid code: " + code);
			}
		}
		throw new IllegalArgumentException("unknown frame type: " + code);
	}

}
