package ru.r2cloud.jradio.cute.fsw;

public enum ReadbackStat {

	BINARY(66), DECIMAL(78), HEX(84), UNKNOWN(255);

	private final int code;

	private ReadbackStat(int code) {
		this.code = code;
	}

	public static ReadbackStat valueOfCode(int code) {
		for (ReadbackStat cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
