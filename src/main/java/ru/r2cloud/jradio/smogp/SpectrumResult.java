package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SpectrumResult {

	private long time;
	private long startFrequency;
	private long stepFrequency;
	private int rbw;
	private int packetIndex;
	private int packetCount;
	private int spectrumLength;
	private int measid;
	private byte[] spectrumData;

	public SpectrumResult() {
		// do nothing
	}

	public SpectrumResult(LittleEndianDataInputStream dis) throws IOException {
		time = dis.readUnsignedInt();
		startFrequency = dis.readUnsignedInt();
		stepFrequency = dis.readUnsignedInt();
		rbw = dis.readUnsignedByte();
		packetIndex = dis.readUnsignedByte();
		packetCount = dis.readUnsignedByte();
		spectrumLength = dis.readUnsignedShort();
		dis.skipBytes(2);
		measid = dis.readUnsignedShort();
		spectrumData = new byte[spectrumLength];
		dis.readFully(spectrumData);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getStartFrequency() {
		return startFrequency;
	}

	public void setStartFrequency(long startFrequency) {
		this.startFrequency = startFrequency;
	}

	public long getStepFrequency() {
		return stepFrequency;
	}

	public void setStepFrequency(long stepFrequency) {
		this.stepFrequency = stepFrequency;
	}

	public int getRbw() {
		return rbw;
	}

	public void setRbw(int rbw) {
		this.rbw = rbw;
	}

	public int getPacketIndex() {
		return packetIndex;
	}

	public void setPacketIndex(int packetIndex) {
		this.packetIndex = packetIndex;
	}

	public int getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}

	public int getSpectrumLength() {
		return spectrumLength;
	}

	public void setSpectrumLength(int spectrumLength) {
		this.spectrumLength = spectrumLength;
	}

	public int getMeasid() {
		return measid;
	}

	public void setMeasid(int measid) {
		this.measid = measid;
	}

	public byte[] getSpectrumData() {
		return spectrumData;
	}

	public void setSpectrumData(byte[] spectrumData) {
		this.spectrumData = spectrumData;
	}

}
