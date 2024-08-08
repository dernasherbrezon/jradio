package ru.r2cloud.jradio.sstk1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class StratosatTk1Beacon extends Ax25Beacon {

	private static final long AX25HEADER_TYPE = 0x848A8286L;

	private Telemetry telemetry;
	private StratosatTk1Header tk1Header;
	private byte[] fileData;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		long type = peakIntoUnsignedInt(data, 0);
		if (type == AX25HEADER_TYPE) {
			super.readBeacon(data);
		} else {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			tk1Header = new StratosatTk1Header(ldis);
			int payloadLength = tk1Header.getFieldSize() - 6;
			if (payloadLength > dis.available()) {
				payloadLength = dis.available();
			}
			if (payloadLength < 0) {
				throw new UncorrectableException("invalid field size");
			}
			switch (tk1Header.getMessageType()) {
			case 0x0905:
			case 0x9820:
				fileData = new byte[payloadLength];
				ldis.readFully(fileData);
				break;
			default:
				unknownPayload = new byte[payloadLength];
				ldis.readFully(unknownPayload);
				break;
			}
		}
	}

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		telemetry = new Telemetry(new LittleEndianDataInputStream(dis));
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public StratosatTk1Header getTk1Header() {
		return tk1Header;
	}

	public void setTk1Header(StratosatTk1Header tk1Header) {
		this.tk1Header = tk1Header;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	private static long peakIntoUnsignedInt(byte[] data, int offset) {
		return (((data[offset + 0] & 0xFF) << 24) | ((data[offset + 1] & 0xFF) << 16) | ((data[offset + 2] & 0xFF) << 8) | (data[offset + 3] & 0xFF)) & 0xFFFFFFFFL;
	}

}
