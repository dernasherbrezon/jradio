package ru.r2cloud.jradio.smogp;

public enum Coding {

	RX(0), TX_AO40_SHORT(1), TX_AO40_LONG(2), TX_RA128(3), TX_RA256(4), TX_PRBS(5);

	private final int id;

	private Coding(int id) {
		this.id = id;
	}

	public static Coding valueOfId(int id) {
		for (Coding cur : values()) {
			if (cur.id == id) {
				return cur;
			}
		}
		return null;
	}

}
