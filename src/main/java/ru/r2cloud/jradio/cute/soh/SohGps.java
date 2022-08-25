package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohGps {

	private int gpsLockCount;
	private boolean gpsValid;
	private boolean gpsEnable;

	public SohGps() {
		// do nothing
	}

	public SohGps(DataInputStream dis) throws IOException {
		gpsLockCount = dis.readUnsignedShort();
		gpsValid = dis.readBoolean();
		gpsEnable = dis.readBoolean();
	}

	public int getGpsLockCount() {
		return gpsLockCount;
	}

	public void setGpsLockCount(int gpsLockCount) {
		this.gpsLockCount = gpsLockCount;
	}

	public boolean isGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(boolean gpsValid) {
		this.gpsValid = gpsValid;
	}

	public boolean isGpsEnable() {
		return gpsEnable;
	}

	public void setGpsEnable(boolean gpsEnable) {
		this.gpsEnable = gpsEnable;
	}

}
