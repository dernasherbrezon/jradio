package ru.r2cloud.jradio.selfiesat;

public enum ObcState {

	IDLE(0), SAFE(1), UNKNOWN(255);

	private final int code;

	private ObcState(int code) {
		this.code = code;
	}

	public static ObcState valueOfCode(int code) {
		for (ObcState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
