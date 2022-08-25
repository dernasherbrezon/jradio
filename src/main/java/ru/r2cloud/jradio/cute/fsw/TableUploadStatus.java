package ru.r2cloud.jradio.cute.fsw;

public enum TableUploadStatus {

	NEW_DATA_IN(0), COMMITTING(1), COMMITTED(2), EXTRACTING(3), EXTRACTED(4), CALCULATING_CRC(5), CRC_READY(6), INVALID_TABLE(7), IDLE(8), FLASH_BURN_COPY(9), FLASH_BURN_DIRECT(10), FLASH_EXTRACT(11), FLASH_BURN_DONE(12), FLASH_EXTRACT_DONE(13), UNKNOWN(255);

	private final int code;

	private TableUploadStatus(int code) {
		this.code = code;
	}

	public static TableUploadStatus valueOfCode(int code) {
		for (TableUploadStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
