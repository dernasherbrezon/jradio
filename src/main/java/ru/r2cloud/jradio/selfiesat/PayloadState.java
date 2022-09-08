package ru.r2cloud.jradio.selfiesat;

public enum PayloadState {

	INIT_STATE(0), IDLE_STATE(1), WAIT_FOR_ACK_STATE(2), FILETRANSFER_STATE(3), SAFE_STATE(4), WAIT_FOR_ACK_SAFE_STATE(5), OFF_STATE(6), DEAD_STATE(7),

	UNKNOWN(255);

	private final int code;

	private PayloadState(int code) {
		this.code = code;
	}

	public static PayloadState valueOfCode(int code) {
		for (PayloadState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
