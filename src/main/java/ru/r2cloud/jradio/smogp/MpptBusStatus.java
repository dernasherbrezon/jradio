package ru.r2cloud.jradio.smogp;

public enum MpptBusStatus {

	NO_DATA(0), VALID_DATA(1), CHANNEL_NUMBER_MISMATCH(-1), CHECKSUM_ERROR(-2), NO_RESPONSE(-3), BUS_ERROR(-4);

	private final byte id;

	private MpptBusStatus(int id) {
		this.id = (byte) id;
	}

	public static MpptBusStatus valueOfId(byte id) {
		for (MpptBusStatus cur : values()) {
			if (cur.id == id) {
				return cur;
			}
		}
		return null;
	}
}
