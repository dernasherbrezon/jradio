package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BeaconInfo {

	private UintValue serialNumber;
	private int rssi;

	public BeaconInfo() {
		// do nothing
	}

	public BeaconInfo(LittleEndianDataInputStream dis) throws IOException {
		serialNumber = new UintValue(dis);
		rssi = dis.readUnsignedByte();
	}

	public static Integer convert5ByteTimestamp(long value) {
		int timeAgo = (int) (value & 0x1F);
		if (timeAgo <= 25) {
			return timeAgo;
		} else if (timeAgo == 26) {
			return 30;
		} else if (timeAgo == 27) {
			return 40;
		} else if (timeAgo == 28) {
			return 60;
		} else if (timeAgo == 29) {
			return 120;
		}
		return null;
	}

	public static Integer convertByteSecondsAgo(int value) {
		value = (value & 0x7F);
		if (value < 126) {
			return value;
		}
		return null;
	}

	public static Integer convertTimeAgo(int value) {
		int timeAgo = (value & 0b111);
		if (timeAgo <= 1) {
			return timeAgo;
		} else if (timeAgo == 2) {
			return 5;
		} else if (timeAgo == 3) {
			return 10;
		} else if (timeAgo == 4) {
			return 20;
		} else if (timeAgo == 5) {
			return 60;
		}
		return null;
	}

	public UintValue getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(UintValue serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

}
