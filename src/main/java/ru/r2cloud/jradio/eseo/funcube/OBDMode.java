package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum OBDMode {

	OBDHPowerUp(0x00), AOCSInitialization(0x01), AOCSDamping(0x02), AOCSNormalSUN(0x04), AOCSNormalECLIPSE(0x08), SafeModeS1(0x10), SafeModeS2(0x20), SafeModeS3(0x40);

	private final int code;
	private static final Map<Integer, OBDMode> typeByCode = new HashMap<>();

	static {
		for (OBDMode cur : OBDMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private OBDMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OBDMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
