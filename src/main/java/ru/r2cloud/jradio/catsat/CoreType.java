package ru.r2cloud.jradio.catsat;

public enum CoreType {

	CHANNELIZER(0), MISSION1_FSK(1), RECORDER(2), TRANSMITTER(3), ASDR_BSP(4), FAILED(127), NONE(255);

	private final int code;

	private CoreType(int code) {
		this.code = code;
	}

	public static CoreType valueOfCode(int code) {
		for (CoreType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return NONE;
	}

}
