package ru.r2cloud.jradio.cute.fsw;

public enum TxPcm {

	NRZL(0), NRZM(1), UNKNOWN(255);

	private final int code;

	private TxPcm(int code) {
		this.code = code;
	}

	public static TxPcm valueOfCode(int code) {
		for (TxPcm cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
