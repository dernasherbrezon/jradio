package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2Eps {

	private long epsTimestamp;
	private int epsMode;
	private int epsSystemCurrent;
	private int epsCellCurrent;
	private int epsCellVoltageHalf;
	private int epsCellVoltageFull;
	private byte temperature1;
	private byte temperature2;

	public Geoscan2Eps() {
		// do nothing
	}

	public Geoscan2Eps(LittleEndianDataInputStream dis) throws IOException {
		epsTimestamp = dis.readUnsignedInt();
		epsMode = dis.readUnsignedByte();
		dis.skipBytes(1);
		epsSystemCurrent = dis.readUnsignedShort();
		epsCellCurrent = dis.readUnsignedShort();
		epsCellVoltageHalf = dis.readUnsignedShort();
		epsCellVoltageFull = dis.readUnsignedShort();
		dis.skipBytes(2);
		temperature1 = dis.readByte();
		temperature2 = dis.readByte();
		dis.skipBytes(5);
	}

	public long getEpsTimestamp() {
		return epsTimestamp;
	}

	public void setEpsTimestamp(long epsTimestamp) {
		this.epsTimestamp = epsTimestamp;
	}

	public int getEpsMode() {
		return epsMode;
	}

	public void setEpsMode(int epsMode) {
		this.epsMode = epsMode;
	}

	public int getEpsSystemCurrent() {
		return epsSystemCurrent;
	}

	public void setEpsSystemCurrent(int epsSystemCurrent) {
		this.epsSystemCurrent = epsSystemCurrent;
	}

	public int getEpsCellCurrent() {
		return epsCellCurrent;
	}

	public void setEpsCellCurrent(int epsCellCurrent) {
		this.epsCellCurrent = epsCellCurrent;
	}

	public int getEpsCellVoltageHalf() {
		return epsCellVoltageHalf;
	}

	public void setEpsCellVoltageHalf(int epsCellVoltageHalf) {
		this.epsCellVoltageHalf = epsCellVoltageHalf;
	}

	public int getEpsCellVoltageFull() {
		return epsCellVoltageFull;
	}

	public void setEpsCellVoltageFull(int epsCellVoltageFull) {
		this.epsCellVoltageFull = epsCellVoltageFull;
	}

	public byte getTemperature1() {
		return temperature1;
	}

	public void setTemperature1(byte temperature1) {
		this.temperature1 = temperature1;
	}

	public byte getTemperature2() {
		return temperature2;
	}

	public void setTemperature2(byte temperature2) {
		this.temperature2 = temperature2;
	}

}
