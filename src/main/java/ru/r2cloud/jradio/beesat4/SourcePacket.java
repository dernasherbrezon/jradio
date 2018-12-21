package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourcePacket {

	private static final Logger LOG = LoggerFactory.getLogger(SourcePacket.class);

	private Apid0 apid0;
	private Apid1 apid1;
	private Apid2 apid2;
	private Apid3 apid3;
	private Apid4 apid4;
	private Apid5 apid5;
	private Apid6_12 apid6_12;
	private Apid13 apid13;
	private Apid14_29 apid14_29;
	private Apid30 apid30;
	private Apid31 apid31;
	private Apid32 apid32;
	private Apid6_12 apid33_39;

	private int PVN; // Packet version number
	private boolean PT; // Packet Type Indicator
	private boolean SHF; // Packet Secondary header flag
	private int APID; // Application process identifier
	private int SEQFLAG; // GroupingFlags
	private int PSC; // Source Sequence Count
	private int PDL; // Packet data length

	public void readExternal(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		PVN = ((raw >> 13) & 0b111);
		PT = ((raw >> 12) & 0b1) > 0;
		SHF = ((raw >> 11) & 0b1) > 0;
		APID = (raw & 0b11111111111);
		raw = dis.readUnsignedShort();
		SEQFLAG = ((raw >> 14) & 0b11);
		PSC = raw & 0x3FFF;
		PDL = dis.readUnsignedShort();
		if (APID == 0) {
			apid0 = new Apid0(dis);
		} else if (APID == 1) {
			apid1 = new Apid1(dis);
		} else if (APID == 2) {
			apid2 = new Apid2(dis);
		} else if (APID == 3) {
			apid3 = new Apid3(dis);
		} else if (APID == 4) {
			apid4 = new Apid4(dis);
		} else if (APID == 5) {
			apid5 = new Apid5(dis);
		} else if (APID >= 6 && APID <= 12) {
			apid6_12 = new Apid6_12(dis);
		} else if (APID == 13) {
			apid13 = new Apid13(dis);
		} else if (APID >= 14 && APID <= 29) {
			apid14_29 = new Apid14_29(dis);
		} else if (APID == 30) {
			apid30 = new Apid30(dis);
		} else if (APID == 31) {
			apid31 = new Apid31(dis);
		} else if (APID == 32) {
			apid32 = new Apid32(dis);
		} else if (APID >= 33 && APID <= 39) {
			apid33_39 = new Apid6_12(dis);
		} else {
			LOG.error("unknown apid: " + APID);
			dis.skipBytes(126);
		}
	}
}
