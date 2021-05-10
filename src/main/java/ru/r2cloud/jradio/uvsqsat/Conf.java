package ru.r2cloud.jradio.uvsqsat;

public enum Conf {

	// Parameters have not been altered since the last load/save.
	ALTERED(0), 
	// Parameters have been altered since the last load/save.
	NOT_ALTERED(1);

	private final int code;

	private Conf(int code) {
		this.code = code;
	}

	public static Conf valueOfCode(int code) {
		for (Conf cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
