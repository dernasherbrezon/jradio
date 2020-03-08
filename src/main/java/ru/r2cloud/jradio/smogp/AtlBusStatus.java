package ru.r2cloud.jradio.smogp;

public enum AtlBusStatus {

	UNKNOWN(0), OK(1), BIT_ERROR(-1), NO_RESPONSE(-2), BUS_ERROR(-3);

	private final byte id;

	private AtlBusStatus(int id) {
		this.id = (byte) id;
	}

	public static AtlBusStatus valueOfId(byte id) {
		for (AtlBusStatus cur : values()) {
			if (cur.id == id) {
				return cur;
			}
		}
		return null;
	}

}
