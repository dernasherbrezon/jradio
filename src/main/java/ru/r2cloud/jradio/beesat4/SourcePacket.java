package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourcePacket {

	private static final Logger LOG = LoggerFactory.getLogger(SourcePacket.class);

	private Apid0 apid0;
	private Apid1 apid1;

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
		} else {
			LOG.error("unknown apid: " + APID);
			dis.skipBytes(126);
		}
	}
}
