package ru.r2cloud.jradio.tubix20;

import java.util.HashMap;
import java.util.Map;

public enum MessageType {

	ACK(0x0), REG(0x1), DIGI(0x2), ECHO(0x3), BAUD(0x4), ERROR_CORRECTION(0x7);

	private final int code;
	private static final Map<Integer, MessageType> typeByCode = new HashMap<>();

	static {
		for (MessageType cur : MessageType.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private MessageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MessageType valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
