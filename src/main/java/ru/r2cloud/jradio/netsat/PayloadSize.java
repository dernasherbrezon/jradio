package ru.r2cloud.jradio.netsat;

public enum PayloadSize {
	
	NO_PAYLOAD(0b00), LARGE(0b01), SMALL(0b10), MEDIUM(0b11), UNKNOWN(255);
	
	private int code;

	private PayloadSize(int code) {
		this.code = code;
	}

	public static PayloadSize valueOfCode(int code) {
		for (PayloadSize cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
