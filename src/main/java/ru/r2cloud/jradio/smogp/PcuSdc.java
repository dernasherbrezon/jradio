package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PcuSdc {

	private long timestamp;
	private Sdc[] sdc;
	private boolean sdc1OvercurrentStatus;
	private boolean sdc1OvervoltageStatus;
	private boolean sdc2OvercurrentStatus;
	private boolean sdc2OvervoltageStatus;

	public PcuSdc() {
		// do nothing
	}

	public PcuSdc(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		sdc = new Sdc[2];
		for (int i = 0; i < sdc.length; i++) {
			sdc[i] = new Sdc(dis);
		}
		int b = dis.readUnsignedByte();
		sdc1OvercurrentStatus = ((b >> 7) & 0x1) > 0;
		sdc1OvervoltageStatus = ((b >> 6) & 0x1) > 0;
		sdc2OvercurrentStatus = ((b >> 5) & 0x1) > 0;
		sdc2OvervoltageStatus = ((b >> 4) & 0x1) > 0;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Sdc[] getSdc() {
		return sdc;
	}

	public void setSdc(Sdc[] sdc) {
		this.sdc = sdc;
	}

	public boolean isSdc1OvercurrentStatus() {
		return sdc1OvercurrentStatus;
	}

	public void setSdc1OvercurrentStatus(boolean sdc1OvercurrentStatus) {
		this.sdc1OvercurrentStatus = sdc1OvercurrentStatus;
	}

	public boolean isSdc1OvervoltageStatus() {
		return sdc1OvervoltageStatus;
	}

	public void setSdc1OvervoltageStatus(boolean sdc1OvervoltageStatus) {
		this.sdc1OvervoltageStatus = sdc1OvervoltageStatus;
	}

	public boolean isSdc2OvercurrentStatus() {
		return sdc2OvercurrentStatus;
	}

	public void setSdc2OvercurrentStatus(boolean sdc2OvercurrentStatus) {
		this.sdc2OvercurrentStatus = sdc2OvercurrentStatus;
	}

	public boolean isSdc2OvervoltageStatus() {
		return sdc2OvervoltageStatus;
	}

	public void setSdc2OvervoltageStatus(boolean sdc2OvervoltageStatus) {
		this.sdc2OvervoltageStatus = sdc2OvervoltageStatus;
	}

}
