package ru.r2cloud.jradio.delfipq;

public enum MessageType {

	REQUEST(1), REPLY(2);

	private final int code;

	private MessageType(int code) {
		this.code = code;
	}

	public static MessageType valueOfCode(int code) {
		for (MessageType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
