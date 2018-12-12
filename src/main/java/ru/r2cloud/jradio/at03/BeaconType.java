package ru.r2cloud.jradio.at03;

import java.util.HashMap;
import java.util.Map;

public enum BeaconType {

	STACIE(0xC0), EPS(0xC1), OBC1(0x53), OBC2(0x56);

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
