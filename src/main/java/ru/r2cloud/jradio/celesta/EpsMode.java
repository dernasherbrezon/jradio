package ru.r2cloud.jradio.celesta;

public enum EpsMode {

	IDLE(0x00), SURVIVAL(0x11), STNADBY(0x22), DEPLOY(0x33), COMMISSIONNONG(0x44), MISSION(0x55), LOW_POWER_MISSION(0x66), SILENT(0x77), UNKNOWN(0xFF);

	private final int code;

	private EpsMode(int code) {
		this.code = code;
	}

	public static EpsMode valueOfCode(int code) {
		for (EpsMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
