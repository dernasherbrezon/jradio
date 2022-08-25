package ru.r2cloud.jradio.cute.fsw;

public enum FsMounted {

	NOT_MOUNTED(0), PRIMARY_MOUNTED(1), REDUNDANT_MOUNTED(2), BOTH_MOUNTED(3), UNKNOWN(255);

	private final int code;

	private FsMounted(int code) {
		this.code = code;
	}

	public static FsMounted valueOfCode(int code) {
		for (FsMounted cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
