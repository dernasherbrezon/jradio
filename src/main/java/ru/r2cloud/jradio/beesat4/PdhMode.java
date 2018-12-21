package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum PdhMode {

	Idle(0), Wait(1), Configuration(2), CaptureMeta(3), CaptureImage(4), SaveMeta(5), SaveImage(6), DeleteSlot(7), ResetSlot(8), InitCamera(11);

	private final int code;
	private final static Map<Integer, PdhMode> typeByCode = new HashMap<>();

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
