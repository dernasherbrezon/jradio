package ru.r2cloud.jradio.ctim;

public enum SeqStopCode {

	NOMINAL(0), CMD(1), INIT(2), REJECT(3), STALE(4), BAD_INT(5), INT_FAIL(6), UNKNOWN(255);

	private final int code;

	private SeqStopCode(int code) {
		this.code = code;
	}

	public static SeqStopCode valueOfCode(int code) {
		for (SeqStopCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
