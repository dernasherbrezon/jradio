package ru.r2cloud.jradio.cute;

public enum ImgProcType {

	SKIP(0), RAW(1), BIN2D(2), BIAS(3), DARK(4), OUTSPEC(5), SPEC1D(6), TRIM2D(7), XDISP(8), UNKNOWN(255);

	private final int code;

	private ImgProcType(int code) {
		this.code = code;
	}

	public static ImgProcType valueOfCode(int code) {
		for (ImgProcType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
