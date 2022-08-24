package ru.r2cloud.jradio.cute;

public enum CcdCtrlState {

	IDLE(0), ERASE(1), OPEN(2), EXPOSE(3), READOUT(4), REPLAY(5), PROCESS(6), ENABLED(7), DISABLED(8), UNKNOWN(255);

	private final int code;

	private CcdCtrlState(int code) {
		this.code = code;
	}

	public static CcdCtrlState valueOfCode(int code) {
		for (CcdCtrlState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
