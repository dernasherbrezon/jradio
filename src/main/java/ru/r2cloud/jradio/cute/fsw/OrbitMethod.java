package ru.r2cloud.jradio.cute.fsw;

public enum OrbitMethod {

	KEPLER(0), GRAVITY_POINT(1), GRAVITY_J2(2), GRAVITY_HARMONIC(3), SGP4(4), EXTERN_ACC_ONLY(5), POLYNOMIAL(6), GEONS(7), UNKNOWN(255);

	private final int code;

	private OrbitMethod(int code) {
		this.code = code;
	}

	public static OrbitMethod valueOfCode(int code) {
		for (OrbitMethod cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
