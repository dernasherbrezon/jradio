package ru.r2cloud.jradio.ax25;

import java.util.HashMap;
import java.util.Map;

public enum UFrameControlType {

	SABME(0b0111111), SABM(0b0011111), DISC(0b0100011), DM(0b0001111), UA(0b0110011), FRMR(0b1000111), UI(0b0000011), XID(0b1011111), TEST(0b1110011);

	private final int code;
	private final static Map<Integer, UFrameControlType> typeByCode = new HashMap<>();

	static {
		for (UFrameControlType cur : UFrameControlType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private UFrameControlType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static UFrameControlType valueOfCode(int code) {
		// shrink byte by removing 5th bit
		code = (code & 0b1111) | ((code & 0b11100000) >> 1);
		return typeByCode.get(code);
	}
}
