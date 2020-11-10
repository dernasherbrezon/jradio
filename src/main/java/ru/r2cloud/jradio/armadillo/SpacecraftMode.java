package ru.r2cloud.jradio.armadillo;

public enum SpacecraftMode {

	NOMINAL(0), LOW_POWER(1);

	private int code;

	private SpacecraftMode(int code) {
		this.code = code;
	}

	public static SpacecraftMode valueOfCode(int code) {
		for (SpacecraftMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
