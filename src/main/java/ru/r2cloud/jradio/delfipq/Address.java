package ru.r2cloud.jradio.delfipq;

public enum Address {

	OBC(1), EPS(2), ADB(3), COMMS(4), ADCS(5), GROUND(8), LOBE(9), OBC2(10);

	private final int code;

	private Address(int code) {
		this.code = code;
	}

	public static Address valueOfCode(int code) {
		for (Address cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
