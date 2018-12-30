package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum ObdMode {

	EGSE(0x00), OBDHPowerUp(0x01), AOCSInitialization(0x02), AOCSDamping(0x03), AOCSNominal(0x04), ReEntry(0xF0), SafeModeS1(0xF1), SafeModeS2(0xF2), SafeModeS3(0xF3), SafeModeS4(0xF4);

	private final int code;
	private final static Map<Integer, ObdMode> typeByCode = new HashMap<>();

	static {
		for (ObdMode cur : ObdMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private ObdMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ObdMode valueOfCode(int code) {
		return typeByCode.get(code);
	}

}
