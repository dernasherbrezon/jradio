package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class SmogPSignallingBeacon extends ru.r2cloud.jradio.Beacon {

	private Integer downlinkSpeed;
	private Coding coding;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		// first 64 bytes are used for synchronization
		// last 6 bytes are actually used
		int sync = 0;
		for (int i = data.length - 6; i < data.length; i++) {
			sync <<= 1;
			// here fec algorithm is "repeat" value
			if (Integer.bitCount(data[i] & 0xFF) > 4) {
				sync |= 1;
			}
		}
		downlinkSpeed = calculateDownlinkSpeed(((sync >> 3) & 0x07));
		coding = Coding.valueOfId((sync & 0x07));
	}

	private static Integer calculateDownlinkSpeed(int downlinkSpeedCode) {
		switch (downlinkSpeedCode) {
		case 0:
			return 500;
		case 1:
			return 1250;
		case 2:
			return 2500;
		case 3:
			return 5000;
		case 4:
			return 12500;
		default:
			return null; // it's ok. FEC was unable to recover
		}
	}

	public int getDownlinkSpeed() {
		return downlinkSpeed;
	}

	public void setDownlinkSpeed(int downlinkSpeed) {
		this.downlinkSpeed = downlinkSpeed;
	}

	public Coding getCoding() {
		return coding;
	}

	public void setCoding(Coding coding) {
		this.coding = coding;
	}

}
