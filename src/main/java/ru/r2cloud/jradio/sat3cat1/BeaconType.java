package ru.r2cloud.jradio.sat3cat1;

import java.util.HashMap;
import java.util.Map;

public enum BeaconType {

	BEACON_TYPE_STATE(0xB0), BEACON_TYPE_VOLT(0xB1), BEACON_TYPE_CURR(0xB2), BEACON_TYPE_TEMP(0xB3), BEACON_TYPE_IRR(0xB4), BEACON_TYPE_SOC(0xB5);

	private final int code;
	private final static Map<Integer, BeaconType> typeByCode = new HashMap<>();

	static {
		for (BeaconType cur : BeaconType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private BeaconType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static BeaconType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
