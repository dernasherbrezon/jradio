package ru.r2cloud.jradio.delfipq;

public enum EpsBusState {

	OFF(0), ON(1);

	private final int code;

	private EpsBusState(int code) {
		this.code = code;
	}

	public static EpsBusState valueOfCode(int code) {
		for (EpsBusState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
