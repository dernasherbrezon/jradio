package ru.r2cloud.jradio.cute.fsw;

public enum AlignUsage {

	NOT_MOUNTED(0), PRIMARY_MOUNTED(1), REDUNDANT_MOUNTED(2), BOTH_MOUNTED(3), UNKNOWN(255);

	private final int code;

	private AlignUsage(int code) {
		this.code = code;
	}

	public static AlignUsage valueOfCode(int code) {
		for (AlignUsage cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
