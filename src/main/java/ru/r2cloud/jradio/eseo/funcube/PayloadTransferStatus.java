package ru.r2cloud.jradio.eseo.funcube;

import java.util.HashMap;
import java.util.Map;

public enum PayloadTransferStatus {

	GetDataFromPayload(0x00), DownlinkDataToGround(0x01);
	
	private final int code;
	private static final Map<Integer, PayloadTransferStatus> typeByCode = new HashMap<>();

	static {
		for (PayloadTransferStatus cur : PayloadTransferStatus.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private PayloadTransferStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PayloadTransferStatus valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
