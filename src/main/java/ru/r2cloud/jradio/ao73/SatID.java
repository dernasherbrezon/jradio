package ru.r2cloud.jradio.ao73;

import java.util.HashMap;
import java.util.Map;

public enum SatID {
	
	FC1EM(0b00), //FUNcube-1 Engineering Model
	FC2(0b01), //FUNcube 2 on UKube
	FC1FM(0b10), //FUNcube-1 Flight Model
	EXTENDED(0b11); //Extended protocol (look elsewhere for the Sat Id!)

	private final int code;
	private final static Map<Integer, SatID> typeByCode = new HashMap<>();

	static {
		for (SatID cur : SatID.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private SatID(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SatID valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
