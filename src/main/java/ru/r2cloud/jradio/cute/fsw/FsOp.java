package ru.r2cloud.jradio.cute.fsw;

public enum FsOp {

	FS_NONE(0), FS_WRITE(1), FS_READ(2), FS_CRC(3), FS_COPY(4), FS_DELETE(5), FS_EXECUTE(6), FS_COMPRESS(7), FS_MOUNT(8), FS_MD5SUM(9), FS_CCSDS_FILE(10), UNKNOWN(255);

	private final int code;

	private FsOp(int code) {
		this.code = code;
	}

	public static FsOp valueOfCode(int code) {
		for (FsOp cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
