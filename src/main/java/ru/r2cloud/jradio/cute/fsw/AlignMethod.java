package ru.r2cloud.jradio.cute.fsw;

public enum AlignMethod {

	NOT_MOUNTED(0), PRIMARY_MOUNTED(1), REDUNDANT_MOUNTED(2), BOTH_MOUNTED(3), UNKNOWN(255);

	private final int code;

	private AlignMethod(int code) {
		this.code = code;
	}

	public static AlignMethod valueOfCode(int code) {
		for (AlignMethod cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
