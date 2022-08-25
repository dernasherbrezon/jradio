package ru.r2cloud.jradio.cute.fsw;

public enum FsOpStatus {

	SUCCESS_DONE(0), IN_PROG(1), BAD_PATH(2), BAD_PARAM(3), FAILURE(4), ABORTED(5), UNKNOWN(255);

	private final int code;

	private FsOpStatus(int code) {
		this.code = code;
	}

	public static FsOpStatus valueOfCode(int code) {
		for (FsOpStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
