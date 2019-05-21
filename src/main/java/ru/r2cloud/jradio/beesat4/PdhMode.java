package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum PdhMode {

	IDLE(0), WAIT(1), CONFIGURATION(2), CAPTUREMETA(3), CAPTUREIMAGE(4), SAVEMETA(5), SAVEIMAGE(6), DELETESLOT(7), RESETSLOT(8), INITCAMERA(11);

	private final int code;
	private static final Map<Integer, PdhMode> typeByCode = new HashMap<>();

	static {
		for (PdhMode cur : PdhMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private PdhMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PdhMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
