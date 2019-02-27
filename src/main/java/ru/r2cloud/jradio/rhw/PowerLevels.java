package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PowerLevels {

	private boolean payload;
	private boolean gps;
	private boolean obc;
	private boolean adcs;
	private boolean batteryHeater1;
	private boolean batteryHeater2;
	private boolean charging;
	private boolean uhfA;
	private boolean uhfB;
	private boolean toggle3v3;
	private boolean toggle5v;
	private boolean antennaDeployment1;
	private boolean antennaDeployment2;
	
	public PowerLevels() {
		//do nothing
	}

	public PowerLevels(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		payload = (raw & 0x1) > 0;
		gps = ((raw >> 1) & 0x1) > 0;
		obc = ((raw >> 2) & 0x1) > 0;
		adcs = ((raw >> 3) & 0x1) > 0;
		batteryHeater1 = ((raw >> 4) & 0x1) > 0;
		batteryHeater2 = ((raw >> 5) & 0x1) > 0;
		charging = ((raw >> 6) & 0x1) > 0;
		uhfA = ((raw >> 7) & 0x1) > 0;
		uhfB = ((raw >> 8) & 0x1) > 0;
		toggle3v3 = ((raw >> 9) & 0x1) > 0;
		toggle5v = ((raw >> 10) & 0x1) > 0;
		antennaDeployment1 = ((raw >> 11) & 0x1) > 0;
		antennaDeployment2 = ((raw >> 12) & 0x1) > 0;
	}

	public boolean isPayload() {
		return payload;
	}

	public void setPayload(boolean payload) {
		this.payload = payload;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public boolean isObc() {
		return obc;
	}

	public void setObc(boolean obc) {
		this.obc = obc;
	}

	public boolean isAdcs() {
		return adcs;
	}

	public void setAdcs(boolean adcs) {
		this.adcs = adcs;
	}

	public boolean isBatteryHeater1() {
		return batteryHeater1;
	}

	public void setBatteryHeater1(boolean batteryHeater1) {
		this.batteryHeater1 = batteryHeater1;
	}

	public boolean isBatteryHeater2() {
		return batteryHeater2;
	}

	public void setBatteryHeater2(boolean batteryHeater2) {
		this.batteryHeater2 = batteryHeater2;
	}

	public boolean isCharging() {
		return charging;
	}

	public void setCharging(boolean charging) {
		this.charging = charging;
	}

	public boolean isUhfA() {
		return uhfA;
	}

	public void setUhfA(boolean uhfA) {
		this.uhfA = uhfA;
	}

	public boolean isUhfB() {
		return uhfB;
	}

	public void setUhfB(boolean uhfB) {
		this.uhfB = uhfB;
	}

	public boolean isToggle3v3() {
		return toggle3v3;
	}

	public void setToggle3v3(boolean toggle3v3) {
		this.toggle3v3 = toggle3v3;
	}

	public boolean isToggle5v() {
		return toggle5v;
	}

	public void setToggle5v(boolean toggle5v) {
		this.toggle5v = toggle5v;
	}

	public boolean isAntennaDeployment1() {
		return antennaDeployment1;
	}

	public void setAntennaDeployment1(boolean antennaDeployment1) {
		this.antennaDeployment1 = antennaDeployment1;
	}

	public boolean isAntennaDeployment2() {
		return antennaDeployment2;
	}

	public void setAntennaDeployment2(boolean antennaDeployment2) {
		this.antennaDeployment2 = antennaDeployment2;
	}

}
