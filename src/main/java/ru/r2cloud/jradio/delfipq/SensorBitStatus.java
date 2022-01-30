package ru.r2cloud.jradio.delfipq;

public enum SensorBitStatus {

	ERROR(0), ACTIVE(1);

	private final int code;

	private SensorBitStatus(int code) {
		this.code = code;
	}

	public static SensorBitStatus valueOfCode(int code) {
		for (SensorBitStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
