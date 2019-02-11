package ru.r2cloud.jradio.technosat;

import java.util.HashMap;
import java.util.Map;

public enum PictureType {

	REC(0), LIVE(1);
	
	private final int code;
	private static final Map<Integer, PictureType> typeByCode = new HashMap<>();

	static {
		for (PictureType cur : PictureType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}
	
	private PictureType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PictureType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
