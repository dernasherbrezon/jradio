package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum SsmAdcChannelStatus {

	OK(0b00), CHOUT0(0b01), CHFixValue(0b10), CHValueMoreTH(0b11);

	private final int code;
	private static final Map<Integer, SsmAdcChannelStatus> typeByCode = new HashMap<>();

	static {
		for (SsmAdcChannelStatus cur : SsmAdcChannelStatus.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private SsmAdcChannelStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SsmAdcChannelStatus valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
