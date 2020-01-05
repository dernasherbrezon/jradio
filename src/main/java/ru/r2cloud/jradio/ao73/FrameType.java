package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum FrameType {

	WO1(0), WO2(1), WO3(2), WO4(3), WO5(4), WO6(5), WO7(6), WO8(7), WO9(8), WO10(9), WO11(10), WO12(11), HR1(12), FM1(13), FM2(14), FM3(15), HR2(16), FM4(17), FM5(18), FM6(19), HR3(20), FM7(21), FM8(22), FM9(23);
	
	private final int code;
	private static final Map<Integer, FrameType> typeByCode = new HashMap<>();

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
			return code + 1;
		}
		if (isHighResolutionData()) {
			switch (code) {
			case 12:
				return 1;
			case 16:
				return 2;
			case 20:
				return 3;
			default:
				throw new IllegalArgumentException("invalid code: " + code);
			}
		}
		if( isFitterMessage() ) {
			switch (code) {
			case 13:
				return 1;
			case 14:
				return 2;
			case 15:
				return 3;
			case 17:
				return 4;
			case 18:
				return 5;
			case 19:
				return 6;
			case 21:
				return 7;
			case 22:
				return 8;
			case 23:
				return 9;
			default:
				throw new IllegalArgumentException("invalid code: " + code);
			}
		}
		throw new IllegalArgumentException("unknown frame type: " + code);
	}

}
