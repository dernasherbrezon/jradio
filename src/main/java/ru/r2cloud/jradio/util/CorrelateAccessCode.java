package ru.r2cloud.jradio.util;

import ru.r2cloud.jradio.blocks.AccessCode;

public class CorrelateAccessCode {

	private int threshold;
	private final AccessCode accessCode;

	public CorrelateAccessCode(int threshold, String access_code) {
		this.threshold = threshold;
		this.accessCode = new AccessCode(access_code);
	}

	public int indexOf(byte[] data) {
		long dataRegister = 0;
		for (int i = 0; i < data.length; i++) {
			dataRegister = (dataRegister << 8) | (data[i] & 0xFF);
			long nwrong = accessCode.correlate(dataRegister);
			if (nwrong <= threshold) {
				return i - accessCode.getLength() / 8 + 1;
			}
		}
		return -1;
	}

	public int lastIndexOf(byte[] data) {
		long dataRegister = 0;
		for (int i = data.length - 1; i >= 0; i--) {
			dataRegister = (dataRegister >> 8) | ((data[i] & 0xFF) << (accessCode.getLength() - 8));
			long nwrong = accessCode.correlate(dataRegister);
			if (nwrong <= threshold) {
				return i;
			}
		}
		return -1;
	}

}
