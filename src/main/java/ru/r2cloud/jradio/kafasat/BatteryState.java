package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BatteryState {

	private int vbat;
	private short cbat;
	private short cbchg;
	private int stack5vCurrent;
	private float temperatureBat1;
	private float temperatureBat2;
	private boolean heater;

	public BatteryState() {
		// do nothing
	}

	public BatteryState(LittleEndianDataInputStream dis) throws IOException {
		vbat = dis.readUnsignedShort();
		cbat = dis.readShort();
		cbchg = dis.readShort();
		stack5vCurrent = dis.readUnsignedShort();
		temperatureBat1 = dis.readShort() * 0.1f;
		temperatureBat2 = dis.readShort() * 0.1f;
		heater = (dis.readUnsignedByte() == 1);
	}

	public int getVbat() {
		return vbat;
	}

	public void setVbat(int vbat) {
		this.vbat = vbat;
	}

	public short getCbat() {
		return cbat;
	}

	public void setCbat(short cbat) {
		this.cbat = cbat;
	}

	public short getCbchg() {
		return cbchg;
	}

	public void setCbchg(short cbchg) {
		this.cbchg = cbchg;
	}

	public int getStack5vCurrent() {
		return stack5vCurrent;
	}

	public void setStack5vCurrent(int stack5vCurrent) {
		this.stack5vCurrent = stack5vCurrent;
	}

	public float getTemperatureBat1() {
		return temperatureBat1;
	}

	public void setTemperatureBat1(float temperatureBat1) {
		this.temperatureBat1 = temperatureBat1;
	}

	public float getTemperatureBat2() {
		return temperatureBat2;
	}

	public void setTemperatureBat2(float temperatureBat2) {
		this.temperatureBat2 = temperatureBat2;
	}

	public boolean isHeater() {
		return heater;
	}

	public void setHeater(boolean heater) {
		this.heater = heater;
	}

}
