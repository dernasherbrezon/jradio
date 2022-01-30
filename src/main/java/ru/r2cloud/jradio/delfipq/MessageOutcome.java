package ru.r2cloud.jradio.delfipq;

public enum MessageOutcome {

	OK(0), ERROR(1);

	private final int code;

	private MessageOutcome(int code) {
		this.code = code;
	}

	public static MessageOutcome valueOfCode(int code) {
		for (MessageOutcome cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
