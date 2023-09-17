package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanEps {

	private long epsTimestamp;
	private float epsCellCurrent;
	private float epsSystemCurrent;
	private float epsCellVoltageHalf;
	private float epsCellVoltageFull;

	public GeoscanEps() {
		// do nothing
	}

	public GeoscanEps(LittleEndianDataInputStream dis) throws IOException {
		epsTimestamp = dis.readUnsignedInt();
		epsCellCurrent = 0.0000766f * dis.readUnsignedInt();
		epsSystemCurrent = 0.00003076f * dis.readUnsignedInt();
		dis.skipBytes(12);
		epsCellVoltageHalf = 0.0176f * dis.readUnsignedByte();
		epsCellVoltageFull = 0.0352f * dis.readUnsignedByte();
		dis.skipBytes(7);
	}

	public long getEpsTimestamp() {
		return epsTimestamp;
	}

	public void setEpsTimestamp(long epsTimestamp) {
		this.epsTimestamp = epsTimestamp;
	}

	public float getEpsCellCurrent() {
		return epsCellCurrent;
	}

	public void setEpsCellCurrent(float epsCellCurrent) {
		this.epsCellCurrent = epsCellCurrent;
	}

	public float getEpsSystemCurrent() {
		return epsSystemCurrent;
	}

	public void setEpsSystemCurrent(float epsSystemCurrent) {
		this.epsSystemCurrent = epsSystemCurrent;
	}

	public float getEpsCellVoltageHalf() {
		return epsCellVoltageHalf;
	}

	public void setEpsCellVoltageHalf(float epsCellVoltageHalf) {
		this.epsCellVoltageHalf = epsCellVoltageHalf;
	}

	public float getEpsCellVoltageFull() {
		return epsCellVoltageFull;
	}

	public void setEpsCellVoltageFull(float epsCellVoltageFull) {
		this.epsCellVoltageFull = epsCellVoltageFull;
	}

}
