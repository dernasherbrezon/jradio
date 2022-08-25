package ru.r2cloud.jradio.cute.fsw;

public enum TxCode {

	NONE(0), CC7_1_2(1), CC7_1_2_IQSWAP(2), CC7_1_2_IG2(3), CC7_1_2_IQSWAP_IG2(4), UNKNOWN(255);

	private final int code;

	private TxCode(int code) {
		this.code = code;
	}

	public static TxCode valueOfCode(int code) {
		for (TxCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
