package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class InterfaceBrdRtc {

	private int timestampHh;
	private int timestampMm;
	private int timestampSs;
	private int timestampDd;
	private int timestampMo;
	private int timestampYy;
	private int timestampDow;
	private short temperature;
	private int antennaStatus;

	public InterfaceBrdRtc() {
		// do nothing
	}

	public InterfaceBrdRtc(LittleEndianDataInputStream dis) throws IOException {
		timestampHh = dis.readUnsignedByte();
		timestampMm = dis.readUnsignedByte();
		timestampSs = dis.readUnsignedByte();
		timestampDd = dis.readUnsignedByte();
		timestampMo = dis.readUnsignedByte();
		timestampYy = dis.readUnsignedByte();
		timestampDow = dis.readUnsignedByte();
		temperature = dis.readShort();
		antennaStatus = dis.readUnsignedByte();
	}

	public int getTimestampHh() {
		return timestampHh;
	}

	public void setTimestampHh(int timestampHh) {
		this.timestampHh = timestampHh;
	}

	public int getTimestampMm() {
		return timestampMm;
	}

	public void setTimestampMm(int timestampMm) {
		this.timestampMm = timestampMm;
	}

	public int getTimestampSs() {
		return timestampSs;
	}

	public void setTimestampSs(int timestampSs) {
		this.timestampSs = timestampSs;
	}

	public int getTimestampDd() {
		return timestampDd;
	}

	public void setTimestampDd(int timestampDd) {
		this.timestampDd = timestampDd;
	}

	public int getTimestampMo() {
		return timestampMo;
	}

	public void setTimestampMo(int timestampMo) {
		this.timestampMo = timestampMo;
	}

	public int getTimestampYy() {
		return timestampYy;
	}

	public void setTimestampYy(int timestampYy) {
		this.timestampYy = timestampYy;
	}

	public int getTimestampDow() {
		return timestampDow;
	}

	public void setTimestampDow(int timestampDow) {
		this.timestampDow = timestampDow;
	}

	public short getTemperature() {
		return temperature;
	}

	public void setTemperature(short temperature) {
		this.temperature = temperature;
	}

	public int getAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(int antennaStatus) {
		this.antennaStatus = antennaStatus;
	}

}
