package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.FoxHeader;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Huskysat1Header extends FoxHeader {

	private boolean safeMode;
	private boolean healthMode;
	private boolean scienceMode;
	private boolean cameraMode;
	private int minorVersion;

	public Huskysat1Header() {
		// do nothing
	}

	public Huskysat1Header(LsbBitInputStream dis) throws IOException {
		super(dis);
		setFoxId(dis.readBitsAsInt(8));
		safeMode = dis.readBit();
		healthMode = dis.readBit();
		scienceMode = dis.readBit();
		cameraMode = dis.readBit();
		minorVersion = dis.readBitsAsInt(4);
	}

	public boolean isSafeMode() {
		return safeMode;
	}

	public void setSafeMode(boolean safeMode) {
		this.safeMode = safeMode;
	}

	public boolean isHealthMode() {
		return healthMode;
	}

	public void setHealthMode(boolean healthMode) {
		this.healthMode = healthMode;
	}

	public boolean isScienceMode() {
		return scienceMode;
	}

	public void setScienceMode(boolean scienceMode) {
		this.scienceMode = scienceMode;
	}

	public boolean isCameraMode() {
		return cameraMode;
	}

	public void setCameraMode(boolean cameraMode) {
		this.cameraMode = cameraMode;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

}
