package ru.r2cloud.jradio.celesta;

public enum TtcMode {

	IDLE(0x01), BEACON(0x11), COMMISSIONNING(0x22), SILENT(0x44), UNKNOWN(0xFF);

	private final int code;

	private TtcMode(int code) {
		this.code = code;
	}

	public static TtcMode valueOfCode(int code) {
		for (TtcMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
