package ru.r2cloud.jradio.selfiesat;

public enum AdcsState {

	INIT_STATE(0), IDLE_STATE(1), WAIT_FOR_ACK_STATE(2), FILETRANSFER_STATE(3), SAFE_STATE(4), WAIT_FOR_ACK_SAFE_STATE(5), OFF_STATE(6), DEAD_STATE(7),

	UNKNOWN(255);

	private final int code;

	private AdcsState(int code) {
		this.code = code;
	}

	public static AdcsState valueOfCode(int code) {
		for (AdcsState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
