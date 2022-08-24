package ru.r2cloud.jradio.cute;

public enum AttStatus {

	OK(0), PENDING(1), BAD(2), TOO_FEW_STARS(3), QUEST_FAILED(4), RESIDUALS_TOO_HIGH(5), TOO_CLOSE_TO_EDGE(6), PIX_AMP_TOO_LOW(7), PIX_AMP_TOO_HIGH(8), BACKGROUND_TOO_HIGH(9), TRACK_FAILURE(10), PIX_SUM_TOO_LOW(11), TOO_DIM_FOR_STARID(13), TOO_MANY_GROUPS(14), TOO_FEW_GROUPS(15),
	CHANNEL_DISABLED(16), TRACK_BLK_OVERLAP(17), OK_FOR_STARID(18), TOO_CLOSE_TO_OTHER(19), TOO_MANY_PIXELS(20), TOO_MANY_COLUMNS(21), TOO_MANY_ROWS(22), OPEN(23), CLOSED(24), RATE_TOO_HIGH(25), UNKNOWN(255);

	private final int code;

	private AttStatus(int code) {
		this.code = code;
	}

	public static AttStatus valueOfCode(int code) {
		for (AttStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
