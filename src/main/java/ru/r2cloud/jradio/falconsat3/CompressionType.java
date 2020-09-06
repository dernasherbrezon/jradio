package ru.r2cloud.jradio.falconsat3;

public enum CompressionType {

	NOT_COMPRESSED(0), PKARC(1), PKZIP(2), LHA(3);

	private final int type;

	private CompressionType(int type) {
		this.type = type;
	}

	public static CompressionType valueOfType(int type) {
		for (CompressionType cur : values()) {
			if (cur.type == type) {
				return cur;
			}
		}
		return null;
	}

}
