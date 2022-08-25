package ru.r2cloud.jradio.cute.fsw;

public enum TxMod {

	BPSK(0),	OQPSK(1), _8PSK(2), UNKNOWN(255);

	private final int code;

	private TxMod(int code) {
		this.code = code;
	}

	public static TxMod valueOfCode(int code) {
		for (TxMod cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
