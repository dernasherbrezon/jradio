package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PcuBus {

	private long timestamp;
	private float unregulatedBusVoltage;
	private float regulatedBusVoltage;
	private int[] obcCurrentConsumption;
	private boolean[] obcOvercurrent;

	public PcuBus() {
		// do nothing
	}

	public PcuBus(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		unregulatedBusVoltage = dis.readUnsignedShort() / 1000.0f;
		regulatedBusVoltage = dis.readUnsignedShort() / 1000.0f;
		obcCurrentConsumption = new int[2];
		for (int i = 0; i < obcCurrentConsumption.length; i++) {
			obcCurrentConsumption[i] = dis.readUnsignedShort();
		}
		int b = dis.readUnsignedByte();
		obcOvercurrent = new boolean[2];
		obcOvercurrent[0] = ((b >> 7) & 0x1) > 0;
		obcOvercurrent[1] = ((b >> 6) & 0x1) > 0;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getUnregulatedBusVoltage() {
		return unregulatedBusVoltage;
	}

	public void setUnregulatedBusVoltage(float unregulatedBusVoltage) {
		this.unregulatedBusVoltage = unregulatedBusVoltage;
	}

	public float getRegulatedBusVoltage() {
		return regulatedBusVoltage;
	}

	public void setRegulatedBusVoltage(float regulatedBusVoltage) {
		this.regulatedBusVoltage = regulatedBusVoltage;
	}

	public int[] getObcCurrentConsumption() {
		return obcCurrentConsumption;
	}

	public void setObcCurrentConsumption(int[] obcCurrentConsumption) {
		this.obcCurrentConsumption = obcCurrentConsumption;
	}

	public boolean[] getObcOvercurrent() {
		return obcOvercurrent;
	}

	public void setObcOvercurrent(boolean[] obcOvercurrent) {
		this.obcOvercurrent = obcOvercurrent;
	}

}
