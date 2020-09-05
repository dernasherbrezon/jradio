package ru.r2cloud.jradio.falconsat3;

public enum WodState {

	OFF(0), START(1), RUNNING(2), STOP(3);
	
	private final int type;

	private WodState(int type) {
		this.type = type;
	}

	public static WodState valueOfType(int type) {
		for (WodState cur : values()) {
			if (cur.type == type) {
				return cur;
			}
		}
		return null;
	}

}
