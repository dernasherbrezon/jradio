package ru.r2cloud.jradio.bsusat1;

public enum RfMessageState {

	CALLSIGN_ERROR(0), ALL_OK(1), SEQNUM_ERROR(2), HASH_ERROR(3), UNKNOWN(-1);

	private final int code;

	private RfMessageState(int code) {
		this.code = code;
	}

	public static RfMessageState valueOfCode(int code) {
		for (RfMessageState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
