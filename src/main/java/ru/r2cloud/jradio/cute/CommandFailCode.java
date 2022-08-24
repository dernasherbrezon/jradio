package ru.r2cloud.jradio.cute;

public enum CommandFailCode {

	SUCCESS(0), MODE(1), ARM(2), SOURCE(3), OPCODE(4), METHOD(5), LENGTH(6), RANGE(7), CHECKSUM(8), PKT_TYPE(9), UNKNOWN(255);

	private final int code;

	private CommandFailCode(int code) {
		this.code = code;
	}

	public static CommandFailCode valueOfCode(int code) {
		for (CommandFailCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
