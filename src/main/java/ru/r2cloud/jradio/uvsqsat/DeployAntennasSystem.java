package ru.r2cloud.jradio.uvsqsat;

public enum DeployAntennasSystem {

	NOMINAL(0), NO_DEPLOY(0x11), DEPLOYMENT_DEBUG(0xDB);

	private final int code;

	private DeployAntennasSystem(int code) {
		this.code = code;
	}

	public static DeployAntennasSystem valueOfCode(int code) {
		for (DeployAntennasSystem cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
