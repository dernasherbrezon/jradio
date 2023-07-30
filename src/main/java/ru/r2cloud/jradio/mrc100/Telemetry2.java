package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry2 {

	private long timestamp;
	private LswCv[] lsw;

	public Telemetry2() {
		// do nothing
	}

	public Telemetry2(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		lsw = new LswCv[28];
		lsw[0] = new LswCv("PCU1", dis);
		lsw[1] = new LswCv("PCU2", dis);
		lsw[2] = new LswCv("SDC1", dis);
		lsw[3] = new LswCv("SDC2", dis);
		lsw[4] = new LswCv("OBC1", dis);
		lsw[5] = new LswCv("OBC2", dis);
		lsw[6] = new LswCv("SPA1", dis);
		lsw[7] = new LswCv("SPA2", dis);
		lsw[8] = new LswCv("COM1", dis);
		lsw[9] = new LswCv("COM2", dis);
		lsw[10] = new LswCv("ADC1", dis);
		lsw[11] = new LswCv("ADC2", dis);
		lsw[12] = new LswCv("ACCU1", dis);
		lsw[13] = new LswCv("ACCU2", dis);
		lsw[14] = new LswCv("SUN1", dis);
		lsw[15] = new LswCv("SUN2", dis);
		lsw[16] = new LswCv("STX1", dis);
		lsw[17] = new LswCv("STX2", dis);
		lsw[18] = new LswCv("TID1", dis);
		lsw[19] = new LswCv("TID2", dis);
		lsw[20] = new LswCv("AIS1", dis);
		lsw[21] = new LswCv("AIS2", dis);
		lsw[22] = new LswCv("GPSCAM1", dis);
		lsw[23] = new LswCv("GPSCAM2", dis);
		lsw[24] = new LswCv("PASSIVE1", dis);
		lsw[25] = new LswCv("PASSIVE2", dis);
		lsw[26] = new LswCv("MPPT12", dis);
		lsw[27] = new LswCv("MPPT34", dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public LswCv[] getLsw() {
		return lsw;
	}

	public void setLsw(LswCv[] lsw) {
		this.lsw = lsw;
	}

}
