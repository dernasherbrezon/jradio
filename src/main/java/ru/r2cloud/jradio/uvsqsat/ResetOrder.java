package ru.r2cloud.jradio.uvsqsat;

public enum ResetOrder {

	ORDER_BY_TC(0xCA), NO_ORDER(0);

	private final int code;

	private ResetOrder(int code) {
		this.code = code;
	}

	public static ResetOrder valueOfCode(int code) {
		for (ResetOrder cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
