package ru.r2cloud.jradio.cute;

public enum TorqueMod {

	OFF(0), ON_POS(1), ON_NEG(2), AUTO(3), MEASURED(4), MODELLED(5), DELAYED_AUTO(6), NO_FIELD_VALID(7), BIDIRECTIONAL(10), POS_ONLY(11), NEG_ONLY(12), UNKNOWN(255);

	private final int code;

	private TorqueMod(int code) {
		this.code = code;
	}

	public static TorqueMod valueOfCode(int code) {
		for (TorqueMod cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
