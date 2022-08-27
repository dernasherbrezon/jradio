package ru.r2cloud.jradio.ctim;

public enum SeqState {

	IDLE(0), ACTIVE(1), SUSPEND(2), PAUSE(3), STALE(4), UNKNOWN(255);

	private final int code;

	private SeqState(int code) {
		this.code = code;
	}

	public static SeqState valueOfCode(int code) {
		for (SeqState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
