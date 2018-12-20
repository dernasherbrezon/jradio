package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum AcsErrorCode {

	UnknownMode(1), WheelSpeed(2), UnknownCommand(7), ToomanySunVecs(20), WrongSunSensor(22), ToofewSunVecs(23), WrongWheelNumber(24);

	private final int code;
	private final static Map<Integer, AcsErrorCode> typeByCode = new HashMap<>();

	static {
		for (AcsErrorCode cur : AcsErrorCode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private AcsErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AcsErrorCode valueOfCode(int code) {
		return typeByCode.get(code);
	}

}
