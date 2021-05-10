package ru.r2cloud.jradio.uvsqsat;

public enum ResetCause {

	POWER_ON(0), WATCHDOG(1), COMMANDED(2), CONTROL_SYSTEM_RESET(3), EMLOPO(4);

	private final int code;

	private ResetCause(int code) {
		this.code = code;
	}

	public static ResetCause valueOfCode(int code) {
		for (ResetCause cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
