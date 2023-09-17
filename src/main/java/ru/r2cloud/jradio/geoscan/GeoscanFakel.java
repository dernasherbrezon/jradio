package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanFakel {

	private long fakelTimestamp;
	private int bitfield0;
	private int bitfield1;
	private float voltage80v;
	private float voltage13v;
	private int currentValve;
	private float voltageValve;
	private int currentControlValve;
	private float voltageControlValve;
	private int currentEk1;
	private float voltageEk1;
	private int currentEk2;
	private float voltageEk2;
	private int currentEk;
	private float voltageEk;
	private float temperatureTd1;
	private int temperatureTp;
	private float pressureCylinder;
	private float pressureReceiver;
	private int switchOnCounter;
	private int timeSwitchedOnCounter;
	private int bitfield3;
	private int state;
	private int pumpTime;
	private int timeOfLastPump;

	public GeoscanFakel() {
		// do nothing
	}

	public GeoscanFakel(LittleEndianDataInputStream dis) throws IOException {
		fakelTimestamp = dis.readUnsignedInt();
		bitfield0 = dis.readUnsignedByte();
		bitfield1 = dis.readUnsignedByte();
		voltage80v = 0.00263f * dis.readUnsignedShort();
		voltage13v = 0.0003443f * dis.readUnsignedShort();
		currentValve = dis.readUnsignedShort();
		voltageValve = 0.0026f * dis.readUnsignedShort();
		currentControlValve = dis.readUnsignedShort();
		voltageControlValve = 0.0026f * dis.readUnsignedShort();
		currentEk1 = dis.readUnsignedShort();
		voltageEk1 = 0.00113f * dis.readUnsignedShort();
		currentEk2 = dis.readUnsignedShort();
		voltageEk2 = 0.00113f * dis.readUnsignedShort();
		currentEk = dis.readUnsignedShort();
		voltageEk = 0.00113f * dis.readUnsignedShort();
		temperatureTd1 = 0.00006f * dis.readUnsignedShort();
		dis.skipBytes(2);
		temperatureTp = dis.readUnsignedShort();
		pressureCylinder = 0.00625f * dis.readUnsignedShort();
		pressureReceiver = 0.0001f * dis.readUnsignedShort();
		switchOnCounter = dis.readUnsignedShort();
		timeSwitchedOnCounter = dis.readUnsignedShort();
		bitfield3 = dis.readUnsignedByte();
		state = dis.readUnsignedByte();
		dis.skipBytes(2);
		pumpTime = dis.readUnsignedShort();
		timeOfLastPump = dis.readUnsignedShort();
		dis.skipBytes(1);
	}

	public long getFakelTimestamp() {
		return fakelTimestamp;
	}

	public void setFakelTimestamp(long fakelTimestamp) {
		this.fakelTimestamp = fakelTimestamp;
	}

	public int getBitfield0() {
		return bitfield0;
	}

	public void setBitfield0(int bitfield0) {
		this.bitfield0 = bitfield0;
	}

	public int getBitfield1() {
		return bitfield1;
	}

	public void setBitfield1(int bitfield1) {
		this.bitfield1 = bitfield1;
	}

	public float getVoltage80v() {
		return voltage80v;
	}

	public void setVoltage80v(float voltage80v) {
		this.voltage80v = voltage80v;
	}

	public float getVoltage13v() {
		return voltage13v;
	}

	public void setVoltage13v(float voltage13v) {
		this.voltage13v = voltage13v;
	}

	public int getCurrentValve() {
		return currentValve;
	}

	public void setCurrentValve(int currentValve) {
		this.currentValve = currentValve;
	}

	public float getVoltageValve() {
		return voltageValve;
	}

	public void setVoltageValve(float voltageValve) {
		this.voltageValve = voltageValve;
	}

	public int getCurrentControlValve() {
		return currentControlValve;
	}

	public void setCurrentControlValve(int currentControlValve) {
		this.currentControlValve = currentControlValve;
	}

	public float getVoltageControlValve() {
		return voltageControlValve;
	}

	public void setVoltageControlValve(float voltageControlValve) {
		this.voltageControlValve = voltageControlValve;
	}

	public int getCurrentEk1() {
		return currentEk1;
	}

	public void setCurrentEk1(int currentEk1) {
		this.currentEk1 = currentEk1;
	}

	public float getVoltageEk1() {
		return voltageEk1;
	}

	public void setVoltageEk1(float voltageEk1) {
		this.voltageEk1 = voltageEk1;
	}

	public int getCurrentEk2() {
		return currentEk2;
	}

	public void setCurrentEk2(int currentEk2) {
		this.currentEk2 = currentEk2;
	}

	public float getVoltageEk2() {
		return voltageEk2;
	}

	public void setVoltageEk2(float voltageEk2) {
		this.voltageEk2 = voltageEk2;
	}

	public int getCurrentEk() {
		return currentEk;
	}

	public void setCurrentEk(int currentEk) {
		this.currentEk = currentEk;
	}

	public float getVoltageEk() {
		return voltageEk;
	}

	public void setVoltageEk(float voltageEk) {
		this.voltageEk = voltageEk;
	}

	public float getTemperatureTd1() {
		return temperatureTd1;
	}

	public void setTemperatureTd1(float temperatureTd1) {
		this.temperatureTd1 = temperatureTd1;
	}

	public int getTemperatureTp() {
		return temperatureTp;
	}

	public void setTemperatureTp(int temperatureTp) {
		this.temperatureTp = temperatureTp;
	}

	public float getPressureCylinder() {
		return pressureCylinder;
	}

	public void setPressureCylinder(float pressureCylinder) {
		this.pressureCylinder = pressureCylinder;
	}

	public float getPressureReceiver() {
		return pressureReceiver;
	}

	public void setPressureReceiver(float pressureReceiver) {
		this.pressureReceiver = pressureReceiver;
	}

	public int getSwitchOnCounter() {
		return switchOnCounter;
	}

	public void setSwitchOnCounter(int switchOnCounter) {
		this.switchOnCounter = switchOnCounter;
	}

	public int getTimeSwitchedOnCounter() {
		return timeSwitchedOnCounter;
	}

	public void setTimeSwitchedOnCounter(int timeSwitchedOnCounter) {
		this.timeSwitchedOnCounter = timeSwitchedOnCounter;
	}

	public int getBitfield3() {
		return bitfield3;
	}

	public void setBitfield3(int bitfield3) {
		this.bitfield3 = bitfield3;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPumpTime() {
		return pumpTime;
	}

	public void setPumpTime(int pumpTime) {
		this.pumpTime = pumpTime;
	}

	public int getTimeOfLastPump() {
		return timeOfLastPump;
	}

	public void setTimeOfLastPump(int timeOfLastPump) {
		this.timeOfLastPump = timeOfLastPump;
	}

}
