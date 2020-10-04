package ru.r2cloud.jradio.strand1;

public enum SwitchStatus {
	
	OFF(0x00), ON(0x01), OFF_OVERCURRENT(0x02), ON_OVERCURRENT(0x05), OFF_TIMEOUT(0x08), OFF_UNKNOWN(0x10); 

	private final int code;

	private SwitchStatus(int code) {
		this.code = code;
	}

	public static SwitchStatus valueOfCode(int code) {
		for (SwitchStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
