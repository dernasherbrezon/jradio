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
		return lastIndexOf(data, data.length - 1);
	}

	public int lastIndexOf(byte[] data, int fromIndex) {
		long dataRegister = 0;
		int i = Math.min(fromIndex, data.length - 1);
		for (; i >= 0; i--) {
			dataRegister = (dataRegister >> 8) | ((data[i] & 0xFF) << (accessCode.getLength() - 8));
			long nwrong = accessCode.correlate(dataRegister);
			if (nwrong <= threshold) {
				return i;
			}
		}
		return -1;
	}

}
