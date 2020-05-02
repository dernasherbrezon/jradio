package ru.r2cloud.jradio.swampsat2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Swampsat2Beacon extends Beacon {

	private static final byte[] ACKNOWLEDGE_BODY = new byte[] { (byte) 0x47, (byte) 0x61, (byte) 0x74, (byte) 0x6f, (byte) 0x72, (byte) 0x20, (byte) 0x4e, (byte) 0x61, (byte) 0x74, (byte) 0x69, (byte) 0x6f, (byte) 0x6e, (byte) 0x20, (byte) 0x49, (byte) 0x73, (byte) 0x20, (byte) 0x45, (byte) 0x76, (byte) 0x65, (byte) 0x72, (byte) 0x79, (byte) 0x77, (byte) 0x68, (byte) 0x65, (byte) 0x72, (byte) 0x65, (byte) 0x21, (byte) 0x20, (byte) 0x46, (byte) 0x72, (byte) 0x6f, (byte) 0x6d, (byte) 0x20,
			(byte) 0x53, (byte) 0x77, (byte) 0x61, (byte) 0x6d, (byte) 0x70, (byte) 0x53, (byte) 0x61, (byte) 0x74, (byte) 0x20, (byte) 0x49, 0x49 };

	private Header header;
	private Boolean acknowledge;
	private Eps eps;
	private Battery battery;
	private Vutrx vutrx;
	private Antennas antennas;
	private Stx stx;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		String callsign = header.getSourceAddress().getCallsign();
		if (!callsign.equals("WK2XID")) {
			throw new UncorrectableException("unsupported callsign: " + callsign);
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		if (isAckMessage(data)) {
			acknowledge = true;
		} else if (dis.available() == 185) {
			eps = new Eps(ldis);
			battery = new Battery(ldis);
			vutrx = new Vutrx(ldis);
			antennas = new Antennas(ldis);
			stx = new Stx(ldis);
		} else if (dis.available() == 163) {
			eps = new Eps(ldis);
			battery = new Battery(ldis);
			vutrx = new Vutrx(ldis);
			antennas = new Antennas(ldis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	private static boolean isAckMessage(byte[] data) {
		if (data.length != Header.LENGTH_BYTES + ACKNOWLEDGE_BODY.length) {
			return false;
		}
		for (int i = 0; i < ACKNOWLEDGE_BODY.length; i++) {
			if (data[i + Header.LENGTH_BYTES] != ACKNOWLEDGE_BODY[i]) {
				return false;
			}
		}
		return true;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Boolean getAcknowledge() {
		return acknowledge;
	}

	public void setAcknowledge(Boolean acknowledge) {
		this.acknowledge = acknowledge;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public Vutrx getVutrx() {
		return vutrx;
	}

	public void setVutrx(Vutrx vutrx) {
		this.vutrx = vutrx;
	}

	public Antennas getAntennas() {
		return antennas;
	}

	public void setAntennas(Antennas antennas) {
		this.antennas = antennas;
	}

	public Stx getStx() {
		return stx;
	}

	public void setStx(Stx stx) {
		this.stx = stx;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
