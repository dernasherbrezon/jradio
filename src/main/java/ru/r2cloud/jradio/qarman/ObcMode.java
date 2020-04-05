package ru.r2cloud.jradio.qarman;

public enum ObcMode {

	INITIALISATION(0), DEPLOYMENT(1), PHASE0(2), PHASE1(3), PHASE2(4), LOW_POWER_MODE(5), PHASE3(6), PHASE4(7), SAFE_MODE(8);

	private final int code;

	private ObcMode(int code) {
		this.code = code;
	}

	public static ObcMode valueOfCode(int code) {
		for (ObcMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
