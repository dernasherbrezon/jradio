package ru.r2cloud.jradio.cute.fsw;

public enum MountStatus {

	NONE(0), MOUNT_DEV0(1), MOUNT_DEV1(2), UNKNOWN(255);

	private final int code;

	private MountStatus(int code) {
		this.code = code;
	}

	public static MountStatus valueOfCode(int code) {
		for (MountStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
