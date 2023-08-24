package ru.r2cloud.jradio.celesta;

public enum ObdhMode {

	STANDBY(0x011), DEPLOY(0x022), COMMISSIONNING(0x033), COMM_PL(0x044), MISSION(0x055), LOW_POWER_MISSION(0x066), SILENT(0x077), POR(0x0FF), UNKNOWN(0x000);

	private final int code;

	private ObdhMode(int code) {
		this.code = code;
	}

	public static ObdhMode valueOfCode(int code) {
		for (ObdhMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
