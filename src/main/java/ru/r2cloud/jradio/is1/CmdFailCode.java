package ru.r2cloud.jradio.is1;

public enum CmdFailCode {

	SUCCESS(0), MODE(1), ARM(2), SOURCE(3), OPCODE(4), METHOD(5), LENGTH(6), RANGE(7), UNKNOWN(255);

	private final int code;

	private CmdFailCode(int code) {
		this.code = code;
	}

	public static CmdFailCode valueOfCode(int code) {
		for (CmdFailCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
