package ru.r2cloud.jradio.cute.fsw;

public enum HeaterCtrlConfig {

	MANUAL_OFF(1), MANUAL_ON(2), SURVIVAL(3), OPERATING(4), UNKNOWN(255);

	private final int code;

	private HeaterCtrlConfig(int code) {
		this.code = code;
	}

	public static HeaterCtrlConfig valueOfCode(int code) {
		for (HeaterCtrlConfig cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
