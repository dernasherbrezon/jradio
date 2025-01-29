package ru.r2cloud.jradio.crocube;

public enum SysState {

	OK(1), POWER_SAVING(2), CRITICAL(3), UNKNOWN(255);

	private final int code;

	private SysState(int code) {
		this.code = code;
	}

	public static SysState valueOfCode(int code) {
		for (SysState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
