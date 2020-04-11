package ru.r2cloud.jradio.meteor;

public enum ElemStatus {
	
	NORM(0), LIMIT_TOP(1), LIMIT_BOTTOM(2), RESERVED(3);

	private int code;

	private ElemStatus(int code) {
		this.code = code;
	}

	public static ElemStatus valueOfCode(int code) {
		for (ElemStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
}
