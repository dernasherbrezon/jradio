package ru.r2cloud.jradio.mobitex;

public enum MessageType {

	ACK(0x0), REG(0x1), DIGI(0x2), ECHO(0x3), BAUD(0x4), ERROR_CORRECTION(0x7), UNKNOWN(255);

	private final int code;

	private MessageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MessageType valueOfCode(int code) {
		for (MessageType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
